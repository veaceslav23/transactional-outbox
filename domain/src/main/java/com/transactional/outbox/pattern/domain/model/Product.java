package com.transactional.outbox.pattern.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    private UUID uuid;
    private String name;
    private Category category;
    private BigDecimal price;
}
