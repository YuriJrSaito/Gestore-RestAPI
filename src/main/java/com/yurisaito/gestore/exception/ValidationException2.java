package com.yurisaito.gestore.exception;

import com.yurisaito.gestore.error.ValidationErrorResponse;

public class ValidationException2 extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final ValidationErrorResponse validationErrorResponse;

    public ValidationException2(ValidationErrorResponse error) {
        super(error.getMessage());
        this.validationErrorResponse = error;
    }

    public ValidationErrorResponse getValidationErrorResponse() {
        return validationErrorResponse;
    }
}