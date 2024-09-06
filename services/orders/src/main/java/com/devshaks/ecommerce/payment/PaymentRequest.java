package com.devshaks.ecommerce.payment;

import com.devshaks.ecommerce.customer.CustomerResponse;
import com.devshaks.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(BigDecimal amount, PaymentMethod paymentMethod, Integer orderId, String orderReference, CustomerResponse customer) {
}
