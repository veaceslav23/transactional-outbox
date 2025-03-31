package com.transactional.outbox.pattern.domain.port;

import com.transactional.outbox.pattern.domain.model.Event;
import com.transactional.outbox.pattern.domain.model.EventStatus;

import java.util.List;

public interface Outbox {

    void saveEvent(Event event, EventStatus eventStatus);

    void updateEvent(Event event, EventStatus eventStatus);

    List<Event> getEventsBy(EventStatus eventStatus);
}
