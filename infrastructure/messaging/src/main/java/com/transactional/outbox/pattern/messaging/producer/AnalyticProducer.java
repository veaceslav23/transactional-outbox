package com.transactional.outbox.pattern.messaging.producer;

import com.transactional.outbox.pattern.domain.model.Event;
import com.transactional.outbox.pattern.domain.port.Analytics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AnalyticProducer implements Analytics {

    @Value("${spring.rabbitmq.analyticsExchange}")
    private String analyticsExchange;

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendAnalytics(Event event) {
        log.info("Send analytics [{}]", event.toString());
        rabbitTemplate.convertAndSend(analyticsExchange, "", event);
    }
}
