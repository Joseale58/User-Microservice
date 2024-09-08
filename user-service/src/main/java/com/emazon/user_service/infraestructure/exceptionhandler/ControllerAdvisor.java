package com.emazon.user_service.infraestructure.exceptionhandler;

import com.emazon.user_service.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "Mensaje:";

    @ExceptionHandler(CellPhoneAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleCellPhoneAlreadyExistsException(
            CellPhoneAlreadyExistsException cellPhoneAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of(MESSAGE, cellPhoneAlreadyExistsException.getMessage()));
    }

    @ExceptionHandler(InvalidPhoneException.class)
    public ResponseEntity<Map<String, String>> handleInvalidPhoneException(
            InvalidPhoneException invalidPhoneException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(MESSAGE, invalidPhoneException.getMessage()));
    }

    @ExceptionHandler(DocumentAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleDocumentAlreadyExistsException(
            DocumentAlreadyExistsException documentAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of(MESSAGE, documentAlreadyExistsException.getMessage()));
    }

    @ExceptionHandler(InvalidDocumentException.class)
    public ResponseEntity<Map<String, String>> handleInvalidDocumentException(
            InvalidDocumentException invalidDocumentException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(MESSAGE, invalidDocumentException.getMessage()));
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExistsException(
            EmailAlreadyExistsException emailAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of(MESSAGE, emailAlreadyExistsException.getMessage()));
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<Map<String, String>> handleInvalidEmailException(
            InvalidEmailException invalidEmailException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(MESSAGE, invalidEmailException.getMessage()));
    }

    @ExceptionHandler(MinorException.class)
    public ResponseEntity<Map<String, String>> handleMinorException(
            MinorException minorException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(MESSAGE, minorException.getMessage()));
    }
    @ExceptionHandler(MissingValueException.class)
    public ResponseEntity<Map<String, String>> handleMissingValueException(
            MissingValueException missingValueException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(MESSAGE, missingValueException.getMessage()));
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleRoleNotFoundException(
            RoleNotFoundException roleNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of(MESSAGE, roleNotFoundException.getMessage()));
    }


}
