package com.yurisaito.gestore.dtos.seller;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SellerResponseDTO(
        @NotNull UUID id,
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String phone,
        @NotBlank String cpf,
        @NotNull LocalDate registrationDate) {
}
