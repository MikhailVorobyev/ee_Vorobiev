package com.accenture.flowershop.exception;

public class NotExistStorageException extends StorageException {

    public NotExistStorageException(String message, Exception e) {
        super(message, e);
    }
}
