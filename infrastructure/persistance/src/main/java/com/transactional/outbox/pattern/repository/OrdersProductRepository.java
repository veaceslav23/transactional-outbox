package com.transactional.outbox.pattern.repository;

import com.transactional.outbox.pattern.entity.OrdersProductId;
import com.transactional.outbox.pattern.entity.OrdersProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersProductRepository extends JpaRepository<OrdersProductsEntity, OrdersProductId> {
}
