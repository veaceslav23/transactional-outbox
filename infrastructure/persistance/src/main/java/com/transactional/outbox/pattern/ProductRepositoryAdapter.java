package com.transactional.outbox.pattern;

import com.transactional.outbox.pattern.domain.model.Category;
import com.transactional.outbox.pattern.domain.model.Product;
import com.transactional.outbox.pattern.domain.port.Products;
import com.transactional.outbox.pattern.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements Products {

    private final ProductRepository repository;

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(productEntity -> new Product(productEntity.getId(), productEntity.getName(), Category.valueOf(productEntity.getCategory()), productEntity.getPrice()))
                .toList();
    }
}
