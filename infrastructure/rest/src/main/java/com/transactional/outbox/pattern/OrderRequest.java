package com.transactional.outbox.pattern;

import com.transactional.outbox.pattern.domain.model.Order;
import com.transactional.outbox.pattern.domain.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private List<ProductRequest> item;

    public Order toOrder() {
        List<Product> result = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (ProductRequest request : item) {
            result.add(request.toProduct());
            totalPrice = request.getPrice().add(totalPrice);
        }
        return Order.builder()
                .product(result)
                .price(totalPrice)
                .build();
    }
}
