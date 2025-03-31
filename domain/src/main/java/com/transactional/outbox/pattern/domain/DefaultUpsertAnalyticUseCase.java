package com.transactional.outbox.pattern.domain;

import com.transactional.outbox.pattern.domain.model.Event;
import com.transactional.outbox.pattern.domain.port.Analytics;

import java.util.List;
import java.util.UUID;

public class DefaultUpsertAnalyticUseCase implements UpsertAnalyticUseCase {

    private final Analytics analytics;

    public DefaultUpsertAnalyticUseCase(Analytics analytics) {
        this.analytics = analytics;
    }

    @Override
    public List<UUID> upsertOrderAnalytic(Event event) {
        return analytics.sendAnalytics(event);
    }
}
