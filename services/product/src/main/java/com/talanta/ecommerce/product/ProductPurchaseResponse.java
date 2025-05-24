package com.talanta.ecommerce.product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductPurchaseResponse(
        UUID productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
