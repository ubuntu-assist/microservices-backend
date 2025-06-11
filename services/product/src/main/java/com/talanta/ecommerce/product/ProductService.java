package com.talanta.ecommerce.product;

import com.talanta.ecommerce.exception.ProductNotFoundException;
import com.talanta.ecommerce.exception.ProductPurchaseException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public UUID createProduct(ProductRequest request) {
        Product product = productRepository.save(productMapper.toProduct(request));
        return product.getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        List<UUID> productIds = request
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        List<Product> storedProducts = productRepository.findAllByIdInOrderById(productIds);

        if(productIds.size() != storedProducts.size())
            throw new ProductPurchaseException("One or more products does not exist");

        List<ProductPurchaseRequest> storedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        List<ProductPurchaseResponse> purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        for (int i = 0; i < storedProducts.size(); i++) {
            Product product = storedProducts.get(i);
            ProductPurchaseRequest productRequest = storedRequest.get(i);

            if(product.getAvailableQuantity() < productRequest.quantity())
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: %s".formatted(productRequest.productId()));

            double newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }

        return purchasedProducts;
    }

    public ProductResponse getSingleProduct(UUID productId) {
        return productRepository.findById(productId)
                .map(productMapper::fromProduct)
                .orElseThrow(() -> new ProductNotFoundException("No product found with the provided ID:: %s".formatted(productId)));
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::fromProduct)
                .toList();
    }

    public void updateProduct(UUID productId, ProductRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("No product found with the provided ID:: %s".formatted(productId)));

        mergeProduct(product, request);
        productRepository.save(product);
    }

    private void mergeProduct(Product product, ProductRequest request) {
        if(StringUtils.isNotBlank(request.name()))
            product.setName(request.name());

        if(StringUtils.isNotBlank(request.description()))
            product.setDescription(request.description());

        if(request.availableQuantity() != null && request.availableQuantity() >= 0)
            product.setAvailableQuantity(request.availableQuantity());

        if(request.price() != null && request.price().compareTo(BigDecimal.ZERO) > 0)
            product.setPrice(request.price());
    }

    public void deleteProduct(UUID productId) {
        boolean exists = productRepository.existsById(productId);

        if(!exists)
            throw new ProductNotFoundException("No product found with the provided ID:: %s".formatted(productId));

        productRepository.deleteById(productId);
    }
}
