package com.sarthak.product.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,
        @NotNull
        String name,
        @NotNull
        String description,
        @Positive
        Double availableQuantity,
        @Positive
        BigDecimal price,
        @NotNull
        Integer categoryId) {
}
