package com.transactional.outbox.pattern.config;

import com.transactional.outbox.pattern.domain.CreateOrderUseCase;
import com.transactional.outbox.pattern.domain.GetProductsUseCase;
import com.transactional.outbox.pattern.domain.port.Analytics;
import com.transactional.outbox.pattern.domain.port.Orders;
import com.transactional.outbox.pattern.domain.port.Outbox;
import com.transactional.outbox.pattern.domain.port.Products;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    GetProductsUseCase getProductsUseCase(Products products) {
        return GetProductsUseCase.getDefaultUseCase(products);
    }

    @Bean
    CreateOrderUseCase createOrderUseCase(Analytics analytics, Orders orders, Outbox outbox) {
        return CreateOrderUseCase.getDefaultUseCase(analytics, orders, outbox);
    }
}
