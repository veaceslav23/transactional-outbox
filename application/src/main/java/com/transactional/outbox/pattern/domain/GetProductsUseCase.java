package com.transactional.outbox.pattern.domain;

import com.transactional.outbox.pattern.domain.model.Product;
import com.transactional.outbox.pattern.domain.port.Products;

import java.util.List;

public interface GetProductsUseCase {

    List<Product> getProducts();

    static GetProductsUseCase getDefaultUseCase(Products products) {
        return new DefaultGetProductsUseCase(products);
    }
}
