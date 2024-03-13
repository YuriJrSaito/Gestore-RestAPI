package com.yurisaito.gestore.utils;

import java.util.Set;

import org.hibernate.validator.internal.engine.path.PathImpl;

import com.yurisaito.gestore.error.ValidationErrorResponse;
import com.yurisaito.gestore.exception.ValidationException2;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ValidationUtil {
    private static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = validatorFactory.getValidator();

    public static <T> void validateEntity(T entity) {
        Set<ConstraintViolation<T>> violations = validator.validate(entity);

        if (!violations.isEmpty()) {
            ValidationErrorResponse errorResponse = new ValidationErrorResponse();
            errorResponse.setMessage("Validation errors occurred");

            for (ConstraintViolation<T> violation : violations) {
                String fieldName = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
                errorResponse.addError(fieldName, violation.getMessage());
            }

            throw new ValidationException2(errorResponse);
        }
    }
}
