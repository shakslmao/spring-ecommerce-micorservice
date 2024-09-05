package com.devshaks.ecommerce.order;

import com.devshaks.ecommerce.customer.CustomerClient;
import com.devshaks.ecommerce.exception.BusinessException;

import com.devshaks.ecommerce.kafka.OrderConfirmation;
import com.devshaks.ecommerce.kafka.OrderProducer;
import com.devshaks.ecommerce.orderline.OrderLineRequest;
import com.devshaks.ecommerce.orderline.OrderLineService;
import com.devshaks.ecommerce.product.ProductClient;
import com.devshaks.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderlineService;
    private final OrderProducer orderProducer;
    private final CustomerClient customerClient;
    private final ProductClient productClient;


    public Integer createOrder(@Valid OrderRequest orderRequest) {
        var customer = this.customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Cannot Create Order: Customer Was Not Found with ID: " + orderRequest.customerId()));
        var purchasedProducts = this.productClient.purchaseProducts(orderRequest.products());
        var order = this.orderRepository.save(orderMapper.toOrder(orderRequest));

        for (PurchaseRequest purchaseRequest : orderRequest.products()) {
            orderlineService.saveOrderLine(new OrderLineRequest(null, order.getId(),purchaseRequest.productId(), purchaseRequest.quantity()));
        }

        orderProducer.sendOrderConfirmation(new OrderConfirmation(
                orderRequest.reference(),
                orderRequest.amount(),
                orderRequest.paymentMethod(),
                customer,
                purchasedProducts));

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream().map(orderMapper::fromOrder).collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException("Order Not Found with ID: " + orderId));
    }
}
