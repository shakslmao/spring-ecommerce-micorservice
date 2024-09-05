package com.devshaks.ecommerce.order;

import com.devshaks.ecommerce.customer.CustomerClient;
import com.devshaks.ecommerce.order.ProductClient;
import com.devshaks.ecommerce.exception.BusinessException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;

    public Integer createOrder(@Valid OrderRequest orderRequest) {
        var customer = this.customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Cannot Create Order: Customer Was Not Found with ID: " + orderRequest.customerId()));

        return null;
    }
}
