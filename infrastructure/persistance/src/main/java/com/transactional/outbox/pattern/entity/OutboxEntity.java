package com.transactional.outbox.pattern.entity;

import com.transactional.outbox.pattern.converter.PropertiesJsonConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "t_outbox")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OutboxEntity {

    @Id
    private UUID id;

    @Column(name = "event", nullable = false)
    @Convert(converter = PropertiesJsonConverter.class)
    @ColumnTransformer(write = "?::jsonb")
    private Map<String, Object> event;

    @Column
    private ZonedDateTime createdAt;

    @Column
    private String status;
}
