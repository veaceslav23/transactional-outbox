package com.transactional.outbox.pattern;

import com.transactional.outbox.pattern.domain.model.Event;
import com.transactional.outbox.pattern.domain.model.Product;
import com.transactional.outbox.pattern.domain.port.Analytics;
import com.transactional.outbox.pattern.entity.AnalyticEntity;
import com.transactional.outbox.pattern.repository.AnalyticRepository;
import com.transactional.outbox.pattern.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Slf4j
@Component
@RequiredArgsConstructor
public class AnalyticRepositoryAdapter implements Analytics {

    private final AnalyticRepository repository;
    private final ProductRepository productRepository;

    @Override
    public List<UUID> sendAnalytics(Event event) {
        log.info("Calculating analytics per product for order [{}]", event.getOrder().toString());
        var products = event.getOrder().getProduct().stream().collect(groupingBy(Product::getUuid, counting()));
        var analytics = products.entrySet().stream()
                .map((entry) -> productRepository.findById(entry.getKey())
                        .map(productEntity -> AnalyticEntity.builder()
                                .product(productEntity)
                                .quantity(entry.getValue().intValue())
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
