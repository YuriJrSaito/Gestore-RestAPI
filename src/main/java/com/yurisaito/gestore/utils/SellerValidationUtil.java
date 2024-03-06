package com.yurisaito.gestore.utils;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;
import com.yurisaito.gestore.exception.ValidationException;

public class SellerValidationUtil {

    public static void validateName(String name, List<ValidationException> exceptions) {
        if (name.isEmpty() || name.trim().isEmpty()) {
            exceptions.add(new ValidationException("Name can't be empty", "name"));
        }
    }

    public static void validateRegistrationDate(LocalDate registrationDate, List<ValidationException> exceptions) {
        if (registrationDate == null) {
            exceptions.add(new ValidationException("Registration date can't be empty", "registrationDate"));
        }
    }

    public static void validateEmail(String email, List<ValidationException> exceptions) {
        if (email == null || email.trim().isEmpty()) {
            exceptions.add(new ValidationException("Invalid email format", "email"));
        }
    }

    public static void validateCPF(String cpf, List<ValidationException> exceptions) {
        if (cpf == null || cpf.trim().isEmpty()) {
            exceptions.add(new ValidationException("Invalid CPF format", "cpf"));
        }
    }

    public static void validatePhone(String phone, List<ValidationException> exceptions) {
        if (phone.isEmpty() || phone.trim().isEmpty()) {
            exceptions.add(new ValidationException("Phone can't be empty", "phone"));
        }
        if(!Pattern.matches("\\d{2}-\\d{5}-\\d{4}", phone)){
            exceptions.add(new ValidationException("Invalid phone format", "phone"));
        }
    }
}