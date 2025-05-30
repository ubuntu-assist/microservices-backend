package com.talanta.ecommerce.orderline;

import java.util.UUID;

public record OrderLineRequest(
        UUID orderId,
        UUID productId,
        double quantity
) {
}
