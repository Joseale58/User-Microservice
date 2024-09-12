package com.emazon.user_service.domain.spi;

import com.emazon.user_service.domain.model.Login;

public interface IAuthenticationPersistencePort {
    String authenticate(Login login);
}
