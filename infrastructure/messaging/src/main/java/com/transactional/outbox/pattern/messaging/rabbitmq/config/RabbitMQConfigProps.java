package com.transactional.outbox.pattern.messaging.rabbitmq.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitMQConfigProps {

    private String host;
    private Integer port;
    private String vhost;
    private String username;
    private String password;
}
