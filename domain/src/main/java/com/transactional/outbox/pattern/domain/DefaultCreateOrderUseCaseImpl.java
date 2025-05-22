package com.transactional.outbox.pattern.domain;

import com.transactional.outbox.pattern.domain.model.Event;
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
        var event = new Event(UUID.randomUUID(), order);
        try {
            analytics.sendAnalytics(event);
            outbox.saveEvent(event, EventStatus.SUCCESS);
        } catch (Exception e) {
            outbox.saveEvent(event, EventStatus.FAILED);
        }
        return orderId;
    }
}
