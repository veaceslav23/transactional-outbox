package com.transactional.outbox.pattern;

import com.transactional.outbox.pattern.domain.model.Event;
import com.transactional.outbox.pattern.domain.model.EventStatus;
import com.transactional.outbox.pattern.domain.port.Analytics;
import com.transactional.outbox.pattern.domain.port.Outbox;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class OutboxSchedulerService {

    private final Outbox outbox;
    private final Analytics analytics;

    @Autowired
    public OutboxSchedulerService(Outbox outbox, @Qualifier("analyticRabbitMqProducerAdapter") Analytics analytics) {
        this.outbox = outbox;
        this.analytics = analytics;
    }

    @Scheduled(fixedRate = 10L, timeUnit = TimeUnit.SECONDS)
    public void invokeOutbox() {
        var failedEvents = outbox.getEventsBy(EventStatus.FAILED);

        log.info("Found [{}] failed events", failedEvents.size());
        log.trace("Found [{}] failed events. Events: [{}]", failedEvents.size(), failedEvents);

        for (Event event : failedEvents) {
            try {
                analytics.sendAnalytics(event);
                log.info("successfully sent event [{}]", event);
                outbox.updateEvent(event, EventStatus.SUCCESS);
            } catch (Exception e) {
                log.error("Failed to send event [{}]", event, e);
                outbox.updateEvent(event, EventStatus.FAILED);
            }
        }
    }
}
