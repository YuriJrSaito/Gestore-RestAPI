package com.yurisaito.gestore.dtos.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductUpdateRequestDTO(
        @NotBlank String name,
        @NotNull Double price) {
    public ProductUpdateRequestDTO {
        // Validações customizadas podem ser feitas no construtor
        if (name != null && name.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be blank");
        }
        if (price == null) {
            throw new IllegalArgumentException("Product price is required");
        }
    }
}
