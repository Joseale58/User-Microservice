package com.emazon.user_service.application.handler;

import com.emazon.user_service.application.dto.LoginDtoRequest;

public interface IAuthenticationHandler {
    String authenticate(LoginDtoRequest loginDtoRequest);
}
