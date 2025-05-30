package com.talanta.ecommerce.kafka;

import com.talanta.ecommerce.customer.CustomerResponse;
import com.talanta.ecommerce.order.PaymentMethod;
import com.talanta.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
