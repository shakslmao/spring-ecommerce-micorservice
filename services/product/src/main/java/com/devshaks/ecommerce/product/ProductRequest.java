package com.devshaks.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,

        @NotNull(message = "Product Name is Required")
        String name,

        @NotNull(message = "Product Description is Required")
        String description,

        @Positive(message = "Product Quantity should be greater than 0")
        double availableQuantity,

        @Positive(message = "Product Price should be greater than 0")
        BigDecimal price,

        @NotNull(message = "Product Category is Required")
        Integer categoryId
) {
}
