package com.transactional.outbox.pattern.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@Entity
@Table(name = "t_product_analytic")
public class ProductAnalyticEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private Integer quantity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "product_id")
    private ProductEntity product;

    @Column
    private LocalDate day;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "analytic_id")
    private AnalyticEntity analytic;
}
