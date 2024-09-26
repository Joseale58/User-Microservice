package com.emazon.user_service.infraestructure.input.rest;


import com.emazon.user_service.application.dto.RegisterDtoRequest;
import com.emazon.user_service.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;

    @Operation(summary = "Register a new user", description = "Register a new user")
    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody RegisterDtoRequest registerDtoRequest) {
        userHandler.registerUser(registerDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Se creó exitosamente el usuario: " + registerDtoRequest.getName());
    }


}
