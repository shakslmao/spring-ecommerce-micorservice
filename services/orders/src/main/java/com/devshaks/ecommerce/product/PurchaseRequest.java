package com.devshaks.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "Product Is Required")
        Integer productId,

        @Positive(message = "Quantity Should Be Positive")
        double quantity
) {
}
