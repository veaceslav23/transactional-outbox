package com.transactional.outbox.pattern.domain.model;

import java.math.BigDecimal;
import java.util.List;

public record Order(
        List<OrderLine> orderLines,
        BigDecimal price
) {
    @Override
    public String toString() {
        return "Order{" +
                "product=" + orderLines +
                ", price=" + price +
                '}';
    }
}
