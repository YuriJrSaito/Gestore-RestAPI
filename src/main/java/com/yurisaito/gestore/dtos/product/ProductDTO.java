package com.yurisaito.gestore.dtos.product;

import com.yurisaito.gestore.entity.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ProductDTO(
		UUID id,
		@NotBlank(message = "productName cannot be blank") String productName,
		@NotBlank(message = "description cannot be blank") String description,
		@NotNull(message = "price cannot be null") double price,
		int stockQuantity,
		@NotBlank(message = "supplier cannot be blank") String supplier,
		@NotBlank(message = "imageUrl cannot be blank") String imageUrl,
		boolean active,
		@NotNull(message = "category cannot be blank") Category category) {
}
