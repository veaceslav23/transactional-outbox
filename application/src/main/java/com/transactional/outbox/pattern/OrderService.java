package com.transactional.outbox.pattern;

import com.transactional.outbox.pattern.domain.CreateOrderUseCase;
import com.transactional.outbox.pattern.domain.model.Order;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CreateOrderUseCase createOrderUseCase;

    @Transactional
    public UUID createOrder(Order order) {
        return createOrderUseCase.createOrder(order);
    }
}
