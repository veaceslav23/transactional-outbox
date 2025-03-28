package com.transactional.outbox.pattern.domain.model;

import java.io.Serializable;
import java.util.UUID;

public class Event implements Serializable {

    private UUID id;
    private Order order;

    public Event() {
    }

    public Event(UUID id, Order order) {
        this.id = id;
        this.order = order;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", order=" + order +
                '}';
    }
}
