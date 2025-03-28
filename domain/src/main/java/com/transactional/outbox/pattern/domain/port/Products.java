package com.transactional.outbox.pattern.domain.port;

import com.transactional.outbox.pattern.domain.model.Product;

import java.util.List;

public interface Products {

    List<Product> getAllProducts();
}
