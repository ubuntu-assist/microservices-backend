package com.talanta.ecommerce.product;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequest(
        @NotBlank(message = "product name is required")
        @Size(min = 2, max = 255, message = "product name must be between 2 and 255 characters")
        String name,

        @Size(max = 255, message = "description cannot exceed 255 characters")
        String description,

        @NotNull(message = "available quantity is required")
        @DecimalMin(value = "0.0", inclusive = true, message = "available quantity must be non-negative")
        @Max(value = 999999, message = "available quantity cannot exceed 999,999")
        Double availableQuantity,

        @NotNull(message = "price is required")
        @DecimalMin(value = "0.01", message = "price must be greater than 0")
        @DecimalMax(value = "999999.99", message = "price cannot exceed 999,999.99")
        @Digits(integer = 6, fraction = 2, message = "price must have at most 6 digits before decimal and 2 after")
        BigDecimal price,

        @NotNull(message = "category id is required")
        UUID categoryId
) {
}