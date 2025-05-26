package com.talanta.ecommerce.product;

import java.math.BigDecimal;
import java.util.UUID;

public record PurchaseResponse(
        UUID productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
