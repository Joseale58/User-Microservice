package com.emazon.user_service.application.mapper;

import com.emazon.user_service.application.dto.LoginDtoResponse;
import org.springframework.stereotype.Component;

@Component
public class LoginDtoResponseManualMapper {

    public LoginDtoResponse toLoginDtoResponse(String login) {
        if(login == null) {
            return null;
        }
        LoginDtoResponse loginDtoResponse = new LoginDtoResponse();
        loginDtoResponse.setToken(login);
        return loginDtoResponse;
    }
}
