package com.talanta.ecommerce.order;

import com.talanta.ecommerce.product.PurchaseRequest;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        @NotBlank(message = "Reference must not be blank")
        @Size(min = 3, max = 50, message = "Reference must be between 3 and 50 characters")
        String reference,

        @NotNull(message = "Total amount must not be null")
        @Positive(message = "Total amount must be positive")
        BigDecimal totalAmount,

        @NotNull(message = "Payment method must not be null")
        PaymentMethod paymentMethod,

        @NotBlank(message = "Customer ID must not be blank")
        @Size(min = 1, max = 36, message = "Customer ID must be between 1 and 36 characters")
        String customerId,

        @NotEmpty(message = "Products list must not be empty")
        @Size(min = 1, message = "At least one product must be included")
        List<PurchaseRequest> products
) {
}
