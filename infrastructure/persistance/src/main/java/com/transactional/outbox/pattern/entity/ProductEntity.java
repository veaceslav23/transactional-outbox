package com.transactional.outbox.pattern.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Entity
@Table(name = "t_product")
public class ProductEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String name;

    @Column
    private BigDecimal price;

    @Column
    private String category;

    @Column
    private Instant createdAt;

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
