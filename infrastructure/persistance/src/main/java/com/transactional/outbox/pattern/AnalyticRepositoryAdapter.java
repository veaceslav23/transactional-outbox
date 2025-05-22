package com.transactional.outbox.pattern;

import com.transactional.outbox.pattern.domain.model.Event;
import com.transactional.outbox.pattern.domain.model.OrderLine;
import com.transactional.outbox.pattern.domain.port.Analytics;
import com.transactional.outbox.pattern.entity.AnalyticEntity;
import com.transactional.outbox.pattern.entity.ProductAnalyticEntity;
import com.transactional.outbox.pattern.repository.AnalyticRepository;
import com.transactional.outbox.pattern.repository.ProductAnalyticRepository;
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

    private final ProductAnalyticRepository productAnalyticRepository;
    private final AnalyticRepository analyticRepository;
    private final ProductRepository productRepository;

    @Override
    public List<UUID> sendAnalytics(Event event) {
        log.info("Calculating analytics per product for order [{}]", event.order().toString());
        analyticRepository.findByIdempotencyId(event.id()).ifPresent((e) -> {
            throw new RuntimeException();
        });

        AnalyticEntity analyticEntity = AnalyticEntity.builder()
                .idempotencyId(event.id())
                .build();
        analyticRepository.saveAndFlush(analyticEntity);
        var products = event.order().orderLines()
                .stream()
                .collect(toMap(orderLine -> orderLine.product().uuid(), OrderLine::quantity));

        var analytics = products.entrySet().stream()
                .map((entry) -> productRepository.findById(entry.getKey())
                        .map(productEntity -> ProductAnalyticEntity.builder()
                                .product(productEntity)
                                .quantity(entry.getValue())
                                .day(LocalDate.now())
                                .analytic(analyticEntity)
                                .build())
                        .orElse(null))
                .filter(Objects::nonNull)
                .toList();

        var result = productAnalyticRepository.saveAllAndFlush(analytics);
        List<UUID> ids = result.stream().map(ProductAnalyticEntity::getId).toList();

        log.info("Saved [{}] analytics", ids.size());
        return ids;
    }
}
