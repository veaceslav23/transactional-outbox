package com.transactional.outbox.pattern.domain;

import com.transactional.outbox.pattern.domain.model.EventStatus;
import com.transactional.outbox.pattern.domain.model.Order;
import com.transactional.outbox.pattern.domain.port.Analytics;
import com.transactional.outbox.pattern.domain.port.Orders;
import com.transactional.outbox.pattern.domain.port.Outbox;

import java.util.UUID;

public class DefaultCreateOrderUseCaseImpl implements CreateOrderUseCase {

    private final Analytics analytics;
    private final Orders orders;
    private final Outbox outbox;

    public DefaultCreateOrderUseCaseImpl(Analytics analytics, Orders orders, Outbox outbox) {
        this.analytics = analytics;
        this.orders = orders;
        this.outbox = outbox;
    }

    @Override
    public UUID createOrder(Order order) {
        var orderId = orders.saveOrder(order);
        try {
            analytics.sendAnalytics(order);
            outbox.saveEvent(order, EventStatus.SUCCESS);
        } catch (Exception e) {
            outbox.saveEvent(order, EventStatus.FAILED);
            throw e;
        }
        return orderId;
    }
}
