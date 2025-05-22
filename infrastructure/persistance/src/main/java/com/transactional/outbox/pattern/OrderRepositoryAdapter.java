package com.transactional.outbox.pattern;

import com.transactional.outbox.pattern.domain.model.Order;
import com.transactional.outbox.pattern.domain.model.OrderLine;
import com.transactional.outbox.pattern.domain.port.Orders;
import com.transactional.outbox.pattern.entity.OrderEntity;
import com.transactional.outbox.pattern.entity.OrderLineEntity;
import com.transactional.outbox.pattern.repository.OrderLineRepository;
import com.transactional.outbox.pattern.repository.OrderRepository;
import com.transactional.outbox.pattern.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements Orders {

    private final OrderRepository repository;
    private final OrderLineRepository orderLineRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public UUID saveOrder(Order order) {
        var orderEntity = OrderEntity.builder()
                .totalPrice(order.price())
                .createdAt(ZonedDateTime.now())
                .build();

        var orderLineEntities = order.orderLines().stream()
                .map(orderLine -> getOrderLineEntity(orderLine, orderEntity))
                .toList();

        var result = repository.save(orderEntity);
        log.info("OrderCreated [{}]", result);
        var x = orderLineRepository.saveAllAndFlush(orderLineEntities);
        log.debug("Order lines created [{}]", x);
        return result.getId();
    }

    private OrderLineEntity getOrderLineEntity(OrderLine orderLine, OrderEntity orderEntity) {
        var productId = orderLine.product().uuid();
        var product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product(%s) not found".formatted(productId)));
        return OrderLineEntity.builder()
                .product(product)
                .order(orderEntity)
                .totalPrice(orderLine.price())
                .build();
    }
}
