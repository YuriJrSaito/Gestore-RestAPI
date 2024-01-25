package com.yurisaito.gestore.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yurisaito.gestore.entity.UserAccess;

public interface AccessRepository extends JpaRepository<UserAccess, UUID>{
	Optional<UserAccess> findByUsername(String username);
}
