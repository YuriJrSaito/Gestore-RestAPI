package com.yurisaito.gestore.dtos.product;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductCreateRequestDTO(
    @NotBlank String name, 
    @NotBlank String description, 
    @NotNull BigDecimal price,
    @NotNull int stockQuantity,
    String supplier,
    String imageUrl,
    @NotNull UUID categoryId) {

}
