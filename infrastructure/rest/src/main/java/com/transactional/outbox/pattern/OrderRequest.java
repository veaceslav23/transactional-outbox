package com.transactional.outbox.pattern;

import com.transactional.outbox.pattern.domain.model.Order;
import com.transactional.outbox.pattern.domain.model.OrderLine;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private List<OrderLineRequest> item;

    public Order toOrder() {
        List<OrderLine> result = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (OrderLineRequest request : item) {
            result.add(request.toOrderLine());
            totalPrice = request.getPrice().add(totalPrice);
        }
        return new Order(result, totalPrice);
    }
}
