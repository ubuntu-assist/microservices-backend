package com.talanta.ecommerce.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByIdInOrderById(List<UUID> productIds);
}
