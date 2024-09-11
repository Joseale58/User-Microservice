package com.emazon.user_service.domain.exception;

public class UserDoesNotExistsException extends RuntimeException {
    public UserDoesNotExistsException(String message) {
        super(message);
    }
}
