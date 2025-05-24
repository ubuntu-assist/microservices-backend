package com.talanta.ecommerce.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<UUID> createProduct(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @PostMapping("purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody List<ProductPurchaseRequest> request
    ) {
        return ResponseEntity.ok(productService.purchaseProducts(request));
    }

    @GetMapping("{product-id}")
    public ResponseEntity<ProductResponse> getSingleProduct(@PathVariable("product-id") UUID productId) {
        return ResponseEntity.ok(productService.getSingleProduct(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PutMapping("{product-id}")
    public ResponseEntity<Void> updateProduct(
            @PathVariable("product-id") UUID productId,
            @RequestBody @Valid ProductRequest request
    ) {
        productService.updateProduct(productId, request);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("{product-id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("product-id") UUID productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.accepted().build();
    }
}
