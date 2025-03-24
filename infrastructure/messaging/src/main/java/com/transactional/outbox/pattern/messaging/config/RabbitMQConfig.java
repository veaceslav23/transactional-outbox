package com.transactional.outbox.pattern.messaging.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {

    private final RabbitMQConfigProps rabbitMQConfigProps;

    @Bean
    CachingConnectionFactory cachingConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setPort(rabbitMQConfigProps.getPort());
        connectionFactory.setHost(rabbitMQConfigProps.getHost());
        connectionFactory.setUsername(rabbitMQConfigProps.getUsername());
        connectionFactory.setPassword(rabbitMQConfigProps.getPassword());
        connectionFactory.setVirtualHost(rabbitMQConfigProps.getVhost());
        return connectionFactory;
    }

    @Bean
    @ConditionalOnBean(CachingConnectionFactory.class)
    RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
