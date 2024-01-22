package com.yurisaito.gestore.dtos;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record CategoryDTO(
        UUID id,
        @NotBlank(message = "Name cannot be blank") String name,
        @NotBlank(message = "Description cannot be blank") String description) {
}
