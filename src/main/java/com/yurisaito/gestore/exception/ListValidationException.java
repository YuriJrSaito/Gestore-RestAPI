package com.yurisaito.gestore.exception;

import java.util.List;

public class ListValidationException extends RuntimeException {

    private final List<ValidationException> validationExceptions;

    public ListValidationException(List<ValidationException> validationExceptions) {
        super(buildErrorMessage(validationExceptions));
        this.validationExceptions = validationExceptions;
    }

    public List<ValidationException> getValidationExceptions() {
        return validationExceptions;
    }

    private static String buildErrorMessage(List<ValidationException> validationExceptions) {
        StringBuilder errorMessage = new StringBuilder("Validation errors occurred: ");
        for (ValidationException exception : validationExceptions) {
            errorMessage.append("[Field: ").append(exception.getFieldName())
                    .append(", Message: ").append(exception.getMessage()).append("] ");
        }
        return errorMessage.toString();
    }
}
