package com.transactional.outbox.pattern.domain.port;

import com.transactional.outbox.pattern.domain.model.Order;

import java.util.UUID;

public interface Orders {

    UUID saveOrder(Order order);
}
