package com.transactional.outbox.pattern.domain;

import com.transactional.outbox.pattern.domain.model.Product;
import com.transactional.outbox.pattern.domain.port.Products;

import java.util.List;

public class DefaultGetProductsUseCase implements GetProductsUseCase {

    private final Products products;

    public DefaultGetProductsUseCase(Products products) {
        this.products = products;
    }

    @Override
    public List<Product> getProducts() {
        return products.getAllProducts();
    }
}
