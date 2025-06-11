package com.talanta.ecommerce.payment;

import com.talanta.ecommerce.customer.CustomerResponse;
import com.talanta.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        UUID orderId,
        String orderReference,
        CustomerResponse customer
) {
}
