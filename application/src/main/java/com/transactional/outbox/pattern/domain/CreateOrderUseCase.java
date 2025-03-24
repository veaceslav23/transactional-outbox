package com.transactional.outbox.pattern.domain;

import com.transactional.outbox.pattern.domain.model.Order;
import com.transactional.outbox.pattern.domain.port.Analytics;
import com.transactional.outbox.pattern.domain.port.Orders;
import com.transactional.outbox.pattern.domain.port.Outbox;

import java.util.UUID;

public interface CreateOrderUseCase {

    UUID createOrder(Order order);

    static CreateOrderUseCase getDefaultUseCase(Analytics analytics, Orders orders, Outbox outbox) {
        return new DefaultCreateOrderUseCaseImpl(analytics, orders, outbox);
    }
}
