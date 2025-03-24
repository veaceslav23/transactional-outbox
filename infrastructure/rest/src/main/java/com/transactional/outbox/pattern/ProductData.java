package com.transactional.outbox.pattern;

import com.transactional.outbox.pattern.domain.model.Category;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
public class ProductData {

    private UUID uuid;
    private String name;
    private Category category;
    private BigDecimal price;
}
