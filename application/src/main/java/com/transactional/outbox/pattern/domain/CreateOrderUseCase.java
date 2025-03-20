package com.transactional.outbox.pattern.domain;

import com.transactional.outbox.pattern.domain.model.Order;
import com.transactional.outbox.pattern.domain.port.Analytics;
import com.transactional.outbox.pattern.domain.port.Orders;

import java.util.UUID;

public interface CreateOrderUseCase {

    UUID createOrder(Order order);

    default CreateOrderUseCase getDefaultUseCase(Analytics analytics, Orders orders) {
        return new DefaultCreateOrderUseCaseImpl(analytics, orders);
    }
}
