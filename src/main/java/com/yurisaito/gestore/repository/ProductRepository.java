package com.yurisaito.gestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yurisaito.gestore.entity.Product;

import io.lettuce.core.dynamic.annotation.Param;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Product findByName(String name);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) = LOWER(:name) AND p.id <> :productId")
    Product findByNameAndIdNot(@Param("name") String name, @Param("productId") UUID productId);
}
