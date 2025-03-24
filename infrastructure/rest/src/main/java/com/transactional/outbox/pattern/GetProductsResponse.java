package com.transactional.outbox.pattern;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class GetProductsResponse {
    List<ProductData> data;
}
