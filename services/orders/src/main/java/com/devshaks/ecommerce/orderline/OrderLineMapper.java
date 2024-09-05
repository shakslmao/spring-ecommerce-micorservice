package com.devshaks.ecommerce.orderline;

import com.devshaks.ecommerce.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.id())
                .quantity(orderLineRequest.quantity())
                .order(Order.builder().id(orderLineRequest.id()).build())
                .productId(orderLineRequest.productId())
                .build();
    }

}
