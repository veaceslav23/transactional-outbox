package com.transactional.outbox.pattern.messaging;

import com.transactional.outbox.pattern.domain.model.Order;
import com.transactional.outbox.pattern.domain.port.Analytics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AnalyticProducer implements Analytics {

    @Value("${spring.rabbitmq.analyticsExchange}")
    private String analyticsExchange;

    private final RabbitTemplate rabbitTemplate;
    private final Jackson2JsonMessageConverter jsonMessageConverter;

    @Override
    public void sendAnalytics(Order order) {
        log.info("Send analytics [{}]", order.toString());
        rabbitTemplate.convertAndSend(analyticsExchange, "", order);
    }
}
