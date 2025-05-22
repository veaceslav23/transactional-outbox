package com.transactional.outbox.pattern;

import com.transactional.outbox.pattern.domain.model.OrderLine;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderLineRequest {

    private ProductRequest productRequest;
    private Integer quantity;
    private BigDecimal price;

    public OrderLine toOrderLine() {
        return new OrderLine(productRequest.toProduct(), quantity, price);
    }

}
