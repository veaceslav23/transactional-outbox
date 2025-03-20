package com.transactional.outbox.pattern.domain;

import com.transactional.outbox.pattern.domain.model.Order;
import com.transactional.outbox.pattern.domain.port.Analytics;
import com.transactional.outbox.pattern.domain.port.Orders;

import java.util.UUID;

public class DefaultCreateOrderUseCaseImpl implements CreateOrderUseCase {

    private final Analytics analytics;
    private final Orders orders;

    public DefaultCreateOrderUseCaseImpl(Analytics analytics, Orders orders) {
        this.analytics = analytics;
        this.orders = orders;
    }

    @Override
    public UUID createOrder(Order order) {
        var orderId = orders.saveOrder(order);
        analytics.sendAnalytics(order);
        return orderId;
    }
}
