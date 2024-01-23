package com.yurisaito.exception;

public class ProductNameDuplicateException extends RuntimeException {

    public ProductNameDuplicateException(String message) {
        super(message);
    }
}
