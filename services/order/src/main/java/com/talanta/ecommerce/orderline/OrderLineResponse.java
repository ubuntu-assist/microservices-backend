package com.talanta.ecommerce.orderline;

import java.util.UUID;

public record OrderLineResponse(
        UUID id,
        UUID productId,
        double quantity
) {
}
