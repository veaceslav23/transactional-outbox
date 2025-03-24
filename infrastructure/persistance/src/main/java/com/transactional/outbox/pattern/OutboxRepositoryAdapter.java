package com.transactional.outbox.pattern;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transactional.outbox.pattern.domain.model.EventStatus;
import com.transactional.outbox.pattern.domain.model.Order;
import com.transactional.outbox.pattern.domain.port.Outbox;
import com.transactional.outbox.pattern.entity.OutboxEntity;
import com.transactional.outbox.pattern.entity.OutboxStatus;
import com.transactional.outbox.pattern.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OutboxRepositoryAdapter implements Outbox {

    private final ObjectMapper objectMapper;
    private final OutboxRepository repository;

    @Override
    public void saveEvent(Order order, EventStatus eventStatus) {
        Map<String, Object> orderMap = objectMapper.convertValue(order, Map.class);
        var outbox = OutboxEntity.builder()
                .event(orderMap)
                .status(OutboxStatus.valueOf(eventStatus.name()).name())
                .createdAt(ZonedDateTime.now())
                .build();
        repository.save(outbox);
    }
}
