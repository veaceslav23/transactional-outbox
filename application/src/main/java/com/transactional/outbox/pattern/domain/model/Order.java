package com.transactional.outbox.pattern.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
public class Order {

    private List<Product> product;
    private BigDecimal price;
}
