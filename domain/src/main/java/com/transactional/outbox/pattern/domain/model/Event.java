package com.transactional.outbox.pattern.domain.model;

import java.util.UUID;

public record Event(
        UUID id,
        Order order
) {
    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", order=" + order +
                '}';
    }
}
