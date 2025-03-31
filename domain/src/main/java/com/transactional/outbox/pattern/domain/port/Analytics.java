package com.transactional.outbox.pattern.domain.port;

import com.transactional.outbox.pattern.domain.model.Event;

import java.util.List;
import java.util.UUID;

public interface Analytics {

    List<UUID> sendAnalytics(Event event);
}
