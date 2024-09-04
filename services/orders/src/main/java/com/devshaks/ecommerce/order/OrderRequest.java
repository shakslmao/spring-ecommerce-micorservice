package com.devshaks.ecommerce.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,

        @Positive(message = "Amount Must be Greater than Zero")
        BigDecimal amount,

        @NotNull(message = "Payment Method is Required")
        PaymentMethod paymentMethod,

        @NotNull(message = "Customer is Required")
        @NotEmpty(message = "Customer is Required")
        @NotBlank(message = "Customer is Required")
        String customerId
) {
}
