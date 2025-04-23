package com.sarthak.product.service;

import com.sarthak.product.entity.Product;
import com.sarthak.product.exceptions.ProductPurchaseException;
import com.sarthak.product.model.ProductPurchaseRequest;
import com.sarthak.product.model.ProductPurchaseResponse;
import com.sarthak.product.model.ProductRequest;
import com.sarthak.product.model.ProductResponse;
import com.sarthak.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepo;
    private final ProductMapper productMapper;

    public Integer createProduct(@Valid ProductRequest request) {
        Product product = productMapper.toProduct(request);
        return productRepo.save(product).getId();
    }


    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        List<Integer> productIds = request.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        List<Product> storedProducts = productRepo.findAllByIdInOrderById(productIds);
        if(storedProducts.size()!=productIds.size()){
            throw new ProductPurchaseException("One or more products does not exist");
        }

        List<ProductPurchaseRequest> storedRequest = request.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        ArrayList<ProductPurchaseResponse> purchasedProducts = new ArrayList<>();

        for(int i=0; i<storedProducts.size(); i++){
            Product product = storedProducts.get(i);
            ProductPurchaseRequest productRequest = storedRequest.get(i);
            if(product.getAvailableQuantity()<productRequest.quantity()){
                throw new ProductPurchaseException(String.format("Product with id %s does not have enough quantity", product.getId()));
            }
            double newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepo.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }

    public ProductResponse findById(Integer productId) {
        return productRepo.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<ProductResponse> findAll() {
        return productRepo.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .toList();
    }
}
