package com.transactional.outbox.pattern;

import com.transactional.outbox.pattern.domain.port.Products;
import com.transactional.outbox.pattern.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductRepositoryAdapter implements Products {

    private final ProductRepository repository;
}
