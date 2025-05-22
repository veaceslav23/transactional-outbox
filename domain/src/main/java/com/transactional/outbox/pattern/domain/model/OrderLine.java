package com.transactional.outbox.pattern.domain.model;

import java.math.BigDecimal;

public record OrderLine(
        Product product,
        Integer quantity,
        BigDecimal price
) {
}
