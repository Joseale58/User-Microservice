package com.emazon.user_service.domain.api;

import com.emazon.user_service.domain.model.Login;

public interface IAuthenticationServicePort {

    String login(Login login);
}
