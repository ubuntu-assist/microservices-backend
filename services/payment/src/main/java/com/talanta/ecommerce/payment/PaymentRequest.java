package com.talanta.ecommerce.payment;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        UUID orderId,
        String orderReference,
        Customer customer
) {
}
