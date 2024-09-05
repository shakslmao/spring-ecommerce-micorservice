package com.devshaks.ecommerce.order;

import com.devshaks.ecommerce.customer.CustomerClient;
import com.devshaks.ecommerce.exception.BusinessException;

import com.devshaks.ecommerce.orderline.OrderLineRequest;
import com.devshaks.ecommerce.product.PurchaseRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private final OrderLineService orderlineService;


    public Integer createOrder(@Valid OrderRequest orderRequest) {
        var customer = this.customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Cannot Create Order: Customer Was Not Found with ID: " + orderRequest.customerId()));
        this.productClient.purchaseProducts(orderRequest.products());
        var order = this.orderRepository.save(orderMapper.toOrder(orderRequest));

        for (PurchaseRequest purchaseRequest : orderRequest.products()) {
            orderlineService.saveOrderLine(new OrderLineRequest(null, order.getId(),purchaseRequest.productId(), purchaseRequest.quantity()));
        }


        return null;
    }
}
