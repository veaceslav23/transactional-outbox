package com.transactional.outbox.pattern.domain.port;

import com.transactional.outbox.pattern.domain.model.EventStatus;
import com.transactional.outbox.pattern.domain.model.Order;

public interface Outbox {

    void saveEvent(Order order, EventStatus eventStatus);
}
