package com.transactional.outbox.pattern.domain.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String name;
    private Category category;
    private BigDecimal price;
}
