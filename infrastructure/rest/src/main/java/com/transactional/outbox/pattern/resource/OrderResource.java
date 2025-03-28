package com.transactional.outbox.pattern.resource;

import com.transactional.outbox.pattern.OrderRequest;
import com.transactional.outbox.pattern.OrderService;
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

    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<String> orders(@RequestBody OrderRequest orderRequest) {
        var oderId = orderService.createOrder(orderRequest.toOrder());
        return ResponseEntity.ok(oderId.toString());
    }
}
