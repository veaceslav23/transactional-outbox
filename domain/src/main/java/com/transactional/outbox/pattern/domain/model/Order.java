package com.transactional.outbox.pattern.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
public class Order implements Serializable {

    private List<Product> product;
    private BigDecimal price;

    @Override
    public String toString() {
        return "Order{" +
                "product=" + product +
                ", price=" + price +
                '}';
    }
}
