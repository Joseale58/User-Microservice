package com.emazon.user_service.application.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class RegisterDtoRequest {
    private Long id;
    private String document;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String cellphone;
    private LocalDate birthdate;
}
