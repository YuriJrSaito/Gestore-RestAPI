package com.yurisaito.gestore.dtos.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductCreateRequestDTO(
        @NotBlank String name,
        @NotNull Double price) {
    public ProductCreateRequestDTO {}
}
