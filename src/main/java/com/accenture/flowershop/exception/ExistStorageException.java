package com.accenture.flowershop.exception;

public class ExistStorageException extends StorageException {

    public ExistStorageException(String message, Exception e) {
        super(message, e);
    }
}
