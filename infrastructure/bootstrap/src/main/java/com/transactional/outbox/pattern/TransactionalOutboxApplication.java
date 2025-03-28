package com.transactional.outbox.pattern;


import com.transactional.outbox.pattern.messaging.config.RabbitMQConfigProps;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableRabbit
@EnableConfigurationProperties(RabbitMQConfigProps.class)
@SpringBootApplication
public class TransactionalOutboxApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionalOutboxApplication.class, args);
    }
}