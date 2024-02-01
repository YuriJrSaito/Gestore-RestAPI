package com.yurisaito.gestore.dtos;

import jakarta.validation.constraints.NotBlank;

public record CategoryDTO(
        @NotBlank(message = "Name cannot be blank") String name,
        @NotBlank(message = "Description cannot be blank") String description) {
}
