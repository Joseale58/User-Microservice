package com.emazon.user_service.infraestructure.input.rest;


import com.emazon.user_service.application.dto.LoginDtoRequest;
import com.emazon.user_service.application.dto.LoginDtoResponse;
import com.emazon.user_service.application.handler.IAuthenticationHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationRestController {

    private final IAuthenticationHandler authenticationHandler;

    @GetMapping
    public ResponseEntity<LoginDtoResponse> login(@RequestBody LoginDtoRequest loginDtoRequest) {
        return ResponseEntity.ok(authenticationHandler.authenticate(loginDtoRequest));
    }
}
