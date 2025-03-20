package com.transactional.outbox.pattern;

import com.transactional.outbox.pattern.domain.CreateOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderResource {

    private CreateOrderUseCase createOrderUseCase;

    @PostMapping("/orders")
    public ResponseEntity<String> orders(@RequestBody OrderRequest orderRequest) {
        var oderId = createOrderUseCase.createOrder(orderRequest.toOrder());
        return ResponseEntity.ok(oderId.toString());
    }
}
