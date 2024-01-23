package com.yurisaito.gestore.dtos.product;

import java.math.BigDecimal;

import com.yurisaito.gestore.entity.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductCreateRequestDTO(
    @NotBlank String name, 
    @NotBlank String description, 
    @NotNull BigDecimal price,
    @NotNull int stockQuantity,
    @NotNull Category category) {

}
