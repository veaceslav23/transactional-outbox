package com.transactional.outbox.pattern.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class OrdersProductId {

    private UUID orderId;
    private UUID productId;
}
