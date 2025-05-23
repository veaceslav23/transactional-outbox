package com.transactional.outbox.pattern.repository;

import com.transactional.outbox.pattern.entity.AnalyticEntity;
import com.transactional.outbox.pattern.entity.ProductAnalyticEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnalyticRepository extends JpaRepository<AnalyticEntity, UUID> {

    Optional<AnalyticEntity> findByIdempotencyId(UUID idempotency);
}
