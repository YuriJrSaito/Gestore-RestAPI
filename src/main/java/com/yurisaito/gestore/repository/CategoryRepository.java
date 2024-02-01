package com.yurisaito.gestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yurisaito.gestore.entity.Category;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
