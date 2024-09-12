package com.emazon.user_service.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoginDtoRequest {
    private String email;
    private String password;
}
