package com.accenture.flowershop.exception;

public class StorageException extends RuntimeException {
    public StorageException(String message, Exception e) {
        super(message, e);
    }
}
