package com.talanta.ecommerce.product;

import jakarta.validation.constraints.*;
import java.util.UUID;

public record ProductPurchaseRequest(
        @NotNull(message = "product id is required")
        UUID productId,

        @NotNull(message = "quantity is required")
        @DecimalMin(value = "0.01", message = "quantity must be greater than 0")
        @Max(value = 999999, message = "quantity cannot exceed 999,999")
        Double quantity
) {
}