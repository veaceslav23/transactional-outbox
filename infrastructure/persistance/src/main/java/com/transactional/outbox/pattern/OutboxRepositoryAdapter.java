package com.transactional.outbox.pattern;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transactional.outbox.pattern.domain.model.Event;
import com.transactional.outbox.pattern.domain.model.EventStatus;
import com.transactional.outbox.pattern.domain.model.Order;
import com.transactional.outbox.pattern.domain.port.Outbox;
import com.transactional.outbox.pattern.entity.OutboxEntity;
import com.transactional.outbox.pattern.entity.OutboxStatus;
import com.transactional.outbox.pattern.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxRepositoryAdapter implements Outbox {

    private final ObjectMapper objectMapper;
    private final OutboxRepository repository;

    @Override
    public void saveEvent(Event event, EventStatus eventStatus) {
        Map<String, Object> orderMap = objectMapper.convertValue(event.order(), Map.class);
        var outbox = OutboxEntity.builder()
                .id(event.id())
                .event(orderMap)
                .status(OutboxStatus.valueOf(eventStatus.name()).name())
                .createdAt(ZonedDateTime.now())
                .build();
        repository.save(outbox);
    }

    @Override
    public void updateEvent(Event event, EventStatus eventStatus) {
        repository.findById(event.id())
                .map(outboxEntity -> {
                    outboxEntity.setStatus(eventStatus.name());
                    return repository.saveAndFlush(outboxEntity);
                })
                .orElseThrow(() -> new RuntimeException("Event(%s) not found".formatted(event.id())));
    }

    @Override
    public List<Event> getEventsBy(EventStatus eventStatus) {
        var outboxExample = Example.of(OutboxEntity.builder().status(eventStatus.name()).build());

        return repository.findBy(outboxExample, FluentQuery.FetchableFluentQuery::all)
                .stream()
                .map(outboxEntity -> new Event(
                        outboxEntity.getId(),
                        objectMapper.convertValue(outboxEntity.getEvent(),
                                Order.class))
                ).toList();
    }
}
