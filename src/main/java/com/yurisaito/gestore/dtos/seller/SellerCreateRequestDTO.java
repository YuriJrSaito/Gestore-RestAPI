package com.yurisaito.gestore.dtos.seller;

import java.util.ArrayList;
import java.util.List;

import com.yurisaito.gestore.dtos.auth.RegisterDTO;
import com.yurisaito.gestore.exception.ListValidationException;
import com.yurisaito.gestore.exception.ValidationException;
import com.yurisaito.gestore.utils.SellerValidationUtil;

import jakarta.validation.constraints.NotNull;

public record SellerCreateRequestDTO(
                String name,
                String email,
                String phone,
                String cpf,
                @NotNull RegisterDTO access) {

        public void validate() {
                List<ValidationException> exceptions = new ArrayList<>();
                SellerValidationUtil.validateName(name, exceptions);
                SellerValidationUtil.validateEmail(email, exceptions);
                SellerValidationUtil.validatePhone(phone, exceptions);
                SellerValidationUtil.validateCPF(cpf, exceptions);

                if (!exceptions.isEmpty()) {
                        throw new ListValidationException(exceptions);
                }
        }
}
