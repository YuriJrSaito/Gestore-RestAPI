package com.yurisaito.gestore.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginResponseDTO(@NotBlank String token) {
    
}
