package com.yurisaito.gestore.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yurisaito.gestore.entity.Seller;

import io.lettuce.core.dynamic.annotation.Param;

public interface SellerRepository extends JpaRepository<Seller, UUID> {
    Seller findByCpf(String cpf);

    @Query("SELECT p FROM Seller p WHERE LOWER(p.cpf) = LOWER(:cpf) AND p.id <> :sellerId")
    Seller findByCpfAndIdNot(@Param("cpf") String cpf, @Param("sellerId") UUID sellerId);
}
