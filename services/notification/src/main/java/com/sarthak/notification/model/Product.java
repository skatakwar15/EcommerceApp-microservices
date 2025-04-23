package com.sarthak.notification.model;

import java.math.BigDecimal;

public record Product(
        Integer productId,
        String name,
        String description,
        Double availableQuantity,
        BigDecimal price,
        Integer categoryId,
        String categoryName,
        String categoryDescription
) {
}
