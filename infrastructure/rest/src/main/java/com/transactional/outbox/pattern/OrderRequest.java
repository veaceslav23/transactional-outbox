package com.transactional.outbox.pattern;

import com.transactional.outbox.pattern.domain.model.Order;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private List<ProductRequest> item;
    private BigDecimal price;

    public Order toOrder() {
        return Order.builder()
                .product(item.stream().map(ProductRequest::toProduct).toList())
                .price(price)
                .build();
    }
}
