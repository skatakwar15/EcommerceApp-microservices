package com.sarthak.order.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Amount should be positive")
        BigDecimal amount,
        @NotNull(message = "Payment method should not be null and be precise")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer id should not be null")
                @NotEmpty(message = "Customer id should not be empty")
                @NotBlank(message = "Customer id should not be blank")
        String customerId,
        @NotEmpty(message = "Products should not be empty")
        List<PurchaseRequest> products
) {
}
