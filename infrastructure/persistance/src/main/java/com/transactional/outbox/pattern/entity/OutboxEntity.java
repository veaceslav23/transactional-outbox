package com.transactional.outbox.pattern.entity;

import com.transactional.outbox.pattern.converter.PropertiesJsonConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "t_outbox")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OutboxEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "event", nullable = false)
    @Convert(converter = PropertiesJsonConverter.class)
    @ColumnTransformer(write = "?::jsonb")
    private Map<String, Object> event;

    @Column
    private ZonedDateTime createdAt;

    @Column
    @Enumerated
    private OutboxStatus status;
}
