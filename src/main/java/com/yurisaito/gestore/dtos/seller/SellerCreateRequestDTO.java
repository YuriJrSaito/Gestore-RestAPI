package com.yurisaito.gestore.dtos.seller;

import java.time.LocalDate;

import com.yurisaito.gestore.entity.UserAccess;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SellerCreateRequestDTO(
        @NotBlank String name, 
        @NotBlank String email, 
        @NotBlank String phone,
        @NotNull LocalDate registrationDate,
        @NotNull UserAccess access){
}
