package com.yurisaito.gestore.dtos.product;

import java.math.BigDecimal;
import java.util.UUID;

import com.yurisaito.gestore.entity.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductResponseDTO(
		@NotNull UUID id,
		@NotBlank(message = "productName cannot be blank") String name,
		@NotBlank(message = "description cannot be blank") String description,
		@NotNull(message = "price cannot be null") BigDecimal price,
		@NotNull int stockQuantity,
		@NotBlank(message = "supplier cannot be blank") String supplier,
		@NotBlank(message = "imageUrl cannot be blank") String imageUrl,
		@NotNull boolean active,
		@NotNull Category categoryId) {
}
