package com.transactional.outbox.pattern.repository;

import com.transactional.outbox.pattern.entity.AnalyticEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnalyticRepository extends JpaRepository<AnalyticEntity, UUID> {

}
