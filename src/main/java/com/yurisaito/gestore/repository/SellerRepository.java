package com.yurisaito.gestore.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yurisaito.gestore.entity.Seller;

public interface SellerRepository extends JpaRepository<Seller, UUID>{
}
