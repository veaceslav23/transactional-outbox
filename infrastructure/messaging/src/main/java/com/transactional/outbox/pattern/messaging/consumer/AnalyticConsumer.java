package com.transactional.outbox.pattern.messaging.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transactional.outbox.pattern.domain.UpsertAnalyticUseCase;
import com.transactional.outbox.pattern.domain.model.Event;
import com.transactional.outbox.pattern.domain.port.Outbox;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AnalyticConsumer {

    private final ObjectMapper objectMapper;
    private final UpsertAnalyticUseCase upsertAnalyticUseCase;

    @RabbitListener(queues = "${spring.rabbitmq.listener.queue}")
    public void consumeAnalytics(Message message) throws IOException {
        var event = objectMapper.readValue(message.getBody(), Event.class);
        log.info("Received message [{}]", event);
        upsertAnalyticUseCase.upsertOrderAnalytic(event);
    }
}
