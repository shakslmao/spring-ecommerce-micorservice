package com.devshaks.ecommerce.kafka;


import com.devshaks.ecommerce.customer.CustomerResponse;
import com.devshaks.ecommerce.order.PaymentMethod;
import com.devshaks.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}
