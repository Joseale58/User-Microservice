package com.emazon.user_service.domain.usecase;

import com.emazon.user_service.domain.api.IAuthenticationServicePort;
import com.emazon.user_service.domain.exception.MissingValueException;
import com.emazon.user_service.domain.exception.UserDoesNotExistsException;
import com.emazon.user_service.domain.model.Login;
import com.emazon.user_service.domain.model.User;
import com.emazon.user_service.domain.spi.ISecurityPersistencePort;
import com.emazon.user_service.domain.spi.IUserPersistencePort;

public class AuthenticationUseCase implements IAuthenticationServicePort {

    private final ISecurityPersistencePort securityPersistencePort;
    private final IUserPersistencePort userPersistencePort;

    public AuthenticationUseCase(ISecurityPersistencePort securityPersistencePort, IUserPersistencePort userPersistencePort) {
        this.securityPersistencePort = securityPersistencePort;
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public String login(Login login) {
        if (login.getEmail() == null || login.getEmail().isEmpty()) {
            throw new MissingValueException("El correo");
        }

        if (login.getPassword() == null || login.getPassword().isEmpty()) {
            throw new MissingValueException("La contraseña");
        }

        User authUser = userPersistencePort.getUserByEmail(login.getEmail());

        if (authUser == null) {
            throw new UserDoesNotExistsException("El usuario con correo: " + login.getEmail() + " no existe");
        }

        return securityPersistencePort.authenticate(login);


    }


}
