package com.transactional.outbox.pattern;

import com.transactional.outbox.pattern.domain.model.Event;
import com.transactional.outbox.pattern.domain.model.OrderLine;
import com.transactional.outbox.pattern.domain.port.Analytics;
import com.transactional.outbox.pattern.entity.AnalyticEntity;
import com.transactional.outbox.pattern.repository.AnalyticRepository;
import com.transactional.outbox.pattern.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.util.stream.Collectors.toMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class AnalyticRepositoryAdapter implements Analytics {

    private final AnalyticRepository repository;
    private final ProductRepository productRepository;

    @Override
    public List<UUID> sendAnalytics(Event event) {
        log.info("Calculating analytics per product for order [{}]", event.order().toString());
        var products = event.order().orderLines()
                .stream()
                .collect(toMap(orderLine -> orderLine.product().uuid(), OrderLine::quantity));

        var analytics = products.entrySet().stream()
                .map((entry) -> productRepository.findById(entry.getKey())
                        .map(productEntity -> AnalyticEntity.builder()
                                .idempotencyId(event.id())
                                .product(productEntity)
                                .quantity(entry.getValue().intValue())
                                .date(LocalDate.now())
                                .build())
                        .orElse(null))
                .filter(Objects::nonNull)
                .toList();
        var result = repository.saveAllAndFlush(analytics);
        List<UUID> ids = result.stream().map(AnalyticEntity::getId).toList();

        log.info("Saved [{}] analytics", ids.size());
        return ids;
    }
}
