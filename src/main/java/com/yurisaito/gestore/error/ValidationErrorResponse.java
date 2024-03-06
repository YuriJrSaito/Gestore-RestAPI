package com.yurisaito.gestore.error;

import java.util.HashMap;
import java.util.Map;

public class ValidationErrorResponse {

    private String message;
    private Map<String, String> errors = new HashMap<>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void addError(String field, String message) {
        errors.put(field, message);
    }
}