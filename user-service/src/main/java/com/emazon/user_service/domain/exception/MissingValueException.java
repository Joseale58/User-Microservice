package com.emazon.user_service.domain.exception;

public class MissingValueException extends RuntimeException {
    public MissingValueException(String field) {
        super(field + " no puede ser nulo");
    }
}
