package com.yurisaito.gestore.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.yurisaito.gestore.entity.UserAccess;

public interface AccessRepository extends JpaRepository<UserAccess, UUID>{
	UserDetails findByUsername(String username);
}
