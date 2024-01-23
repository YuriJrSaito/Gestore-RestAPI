package com.yurisaito.gestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yurisaito.gestore.entity.Product;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Product findByName(String name);
}
