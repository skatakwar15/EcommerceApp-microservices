package com.sarthak.product.service;

import com.sarthak.product.entity.Category;
import com.sarthak.product.entity.Product;
import com.sarthak.product.model.ProductPurchaseResponse;
import com.sarthak.product.model.ProductRequest;
import com.sarthak.product.model.ProductResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProduct(@Valid ProductRequest request) {
        return Product
                .builder()
                .id(request.id())
                .name(request.name())
                .price(request.price())
                .availableQuantity(request.availableQuantity())
                .description(request.description())
                .category(Category.builder().id(request.categoryId()).build())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(), product.getName(), product.getDescription(), product.getPrice(), quantity
        );
    }
}
