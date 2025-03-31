package com.transactional.outbox.pattern.domain;

import com.transactional.outbox.pattern.domain.model.Event;
import com.transactional.outbox.pattern.domain.port.Analytics;

import java.util.List;
import java.util.UUID;

public interface UpsertAnalyticUseCase {

    List<UUID> upsertOrderAnalytic(Event event);

    static DefaultUpsertAnalyticUseCase defaultUseCase(Analytics analytics) {
        return new DefaultUpsertAnalyticUseCase(analytics);
    }
}
