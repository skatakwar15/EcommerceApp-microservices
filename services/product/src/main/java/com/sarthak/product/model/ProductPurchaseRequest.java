package com.sarthak.product.model;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull
        Integer productId,
        @NotNull
        double quantity
) {
}
