package com.transactional.outbox.pattern;

import com.transactional.outbox.pattern.domain.model.Category;
import com.transactional.outbox.pattern.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private String name;
    private String category;
    private BigDecimal price;

    public Product toProduct() {
        return new Product(name, Category.valueOf(category), price);
    }
}
