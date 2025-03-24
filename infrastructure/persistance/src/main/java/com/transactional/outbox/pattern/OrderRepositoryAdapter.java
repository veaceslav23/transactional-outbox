package com.transactional.outbox.pattern;

import com.transactional.outbox.pattern.domain.model.Order;
import com.transactional.outbox.pattern.domain.model.Product;
import com.transactional.outbox.pattern.domain.port.Orders;
import com.transactional.outbox.pattern.entity.OrderEntity;
import com.transactional.outbox.pattern.entity.OrdersProductId;
import com.transactional.outbox.pattern.entity.OrdersProductsEntity;
import com.transactional.outbox.pattern.repository.OrderRepository;
import com.transactional.outbox.pattern.repository.OrdersProductRepository;
import com.transactional.outbox.pattern.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements Orders {

    private final OrderRepository repository;
    private final OrdersProductRepository ordersProductRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public UUID saveOrder(Order order) {
        var productIds = order.getProduct().stream().map(Product::getUuid).toList();
        var product = productRepository.findAllById(productIds);
        var orderEntity = OrderEntity.builder()
                .totalPrice(order.getPrice())
                .createdAt(ZonedDateTime.now())
                .build();

        var ordersProducts = product.stream().map(productEntity -> OrdersProductsEntity.builder()
                .product(productEntity)
                .order(orderEntity)
                .quantity(BigDecimal.valueOf(1))
                .id(new OrdersProductId())
                .build()).toList();

        var result = repository.save(orderEntity);
        log.info("OrderCreated [{}]", result);
        var x = ordersProductRepository.saveAllAndFlush(ordersProducts);
        log.info("Orders created [{}]", x);
        return result.getId();
    }
}
