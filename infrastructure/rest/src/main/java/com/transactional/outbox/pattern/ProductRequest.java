package com.transactional.outbox.pattern;

import com.transactional.outbox.pattern.domain.model.Category;
import com.transactional.outbox.pattern.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private UUID uuid;
    private String name;
    private String category;
    private BigDecimal pricePerItem;
    private Integer quantity;

    public Product toProduct() {
        return new Product(uuid, name, Category.valueOf(category), pricePerItem);
    }
}
