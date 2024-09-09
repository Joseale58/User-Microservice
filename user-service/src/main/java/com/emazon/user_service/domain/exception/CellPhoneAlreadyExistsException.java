package com.emazon.user_service.domain.exception;

public class CellPhoneAlreadyExistsException extends RuntimeException {
    public CellPhoneAlreadyExistsException(String message) {
        super(message);
    }
}
