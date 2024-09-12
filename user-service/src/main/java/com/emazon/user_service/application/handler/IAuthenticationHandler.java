package com.emazon.user_service.application.handler;

import com.emazon.user_service.application.dto.LoginDtoRequest;
import com.emazon.user_service.application.dto.LoginDtoResponse;

public interface IAuthenticationHandler {
    LoginDtoResponse authenticate(LoginDtoRequest loginDtoRequest);
}
