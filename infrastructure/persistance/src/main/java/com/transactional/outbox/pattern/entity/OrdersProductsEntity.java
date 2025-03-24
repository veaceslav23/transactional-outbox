package com.transactional.outbox.pattern.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "t_orders_products")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersProductsEntity {

    @EmbeddedId
    private OrdersProductId id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(referencedColumnName = "id")
    private OrderEntity order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(referencedColumnName = "id")
    private ProductEntity product;

    @Column
    private BigDecimal quantity;
}
