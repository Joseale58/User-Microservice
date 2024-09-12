package com.emazon.user_service.domain.spi;

import com.emazon.user_service.domain.model.Login;
import org.springframework.security.core.Authentication;

public interface ISecurityPersistencePort {

        String encryptPassword(String password);

        String authenticate(Login login);

}
