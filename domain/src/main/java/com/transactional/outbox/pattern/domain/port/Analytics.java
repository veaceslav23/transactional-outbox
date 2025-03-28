package com.transactional.outbox.pattern.domain.port;

import com.transactional.outbox.pattern.domain.model.Event;
import com.transactional.outbox.pattern.domain.model.Order;

public interface Analytics {

    void sendAnalytics(Event event);
}
