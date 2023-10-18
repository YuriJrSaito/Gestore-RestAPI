package com.yurisaito.gestore.dtos;

import com.yurisaito.gestore.entity.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(@NotBlank String username, @NotBlank String password, @NotNull UserRole role) {
	
}
