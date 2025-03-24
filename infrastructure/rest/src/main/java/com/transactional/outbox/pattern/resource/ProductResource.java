package com.transactional.outbox.pattern.resource;

import com.transactional.outbox.pattern.GetProductsResponse;
import com.transactional.outbox.pattern.ProductData;
import com.transactional.outbox.pattern.domain.GetProductsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductResource {

    private final GetProductsUseCase getProductsUseCase;

    @GetMapping("/products")
    public ResponseEntity<GetProductsResponse> products() {
        var products = getProductsUseCase.getProducts()
                .stream()
                .map(product -> ProductData.builder()
                        .uuid(product.getUuid())
                        .price(product.getPrice())
                        .name(product.getName())
                        .category(product.getCategory())
                        .build())
                .toList();

        return ResponseEntity.ok(GetProductsResponse.builder().data(products).build());
    }
}
