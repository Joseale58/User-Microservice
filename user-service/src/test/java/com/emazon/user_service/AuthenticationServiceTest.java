package com.emazon.user_service;

import com.emazon.user_service.domain.exception.MissingValueException;
import com.emazon.user_service.domain.exception.UserDoesNotExistsException;
import com.emazon.user_service.domain.model.Login;
import com.emazon.user_service.domain.model.Role;
import com.emazon.user_service.domain.model.User;
import com.emazon.user_service.domain.spi.ISecurityPersistencePort;
import com.emazon.user_service.domain.spi.IUserPersistencePort;
import com.emazon.user_service.domain.usecase.AuthenticationUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class AuthenticationServiceTest {

    private ISecurityPersistencePort securityPersistencePort;
    private IUserPersistencePort userPersistencePort;
    private AuthenticationUseCase authenticationUseCase;
    private Login login;

    @BeforeEach
    public void setUp() {
        securityPersistencePort = Mockito.mock(ISecurityPersistencePort.class);
        userPersistencePort = Mockito.mock(IUserPersistencePort.class);
        authenticationUseCase = new AuthenticationUseCase(securityPersistencePort, userPersistencePort);
        login = new Login("user@example.com", "password123");
    }

    @Test
    void testLogin_NullEmail_ThrowsMissingValueException() {
        login.setEmail(null);
        assertThrows(MissingValueException.class, () -> {
            authenticationUseCase.login(login);
        });
    }

    @Test
    void testLogin_EmptyEmail_ThrowsMissingValueException() {
        login.setEmail("");
        assertThrows(MissingValueException.class, () -> {
            authenticationUseCase.login(login);
        });
    }

    @Test
    void testLogin_NullPassword_ThrowsMissingValueException() {
        login.setPassword(null);
        assertThrows(MissingValueException.class, () -> {
            authenticationUseCase.login(login);
        });
    }

    @Test
    void testLogin_EmptyPassword_ThrowsMissingValueException() {
        login.setPassword("");
        assertThrows(MissingValueException.class, () -> {
            authenticationUseCase.login(login);
        });
    }

    @Test
    void testLogin_UserDoesNotExist_ThrowsUserDoesNotExistsException() {
        when(userPersistencePort.getUserByEmail(anyString())).thenReturn(null);
        assertThrows(UserDoesNotExistsException.class, () -> {
            authenticationUseCase.login(login);
        });
    }

    @Test
    void testLogin_ValidLogin_ReturnsAuthenticationToken() {
        User user = new User(1L, "12345","Juana", "Martinez","juanamartinez@email.com","contra","3004005050",LocalDate.of(1995, 5, 10),new Role(1L,"admin","Adminsitrador"));
        when(userPersistencePort.getUserByEmail(anyString())).thenReturn(user);
        when(securityPersistencePort.authenticate(login)).thenReturn("authToken");

        String result = authenticationUseCase.login(login);

        verify(userPersistencePort, times(1)).getUserByEmail(login.getEmail());
        verify(securityPersistencePort, times(1)).authenticate(login);
        assert(result.equals("authToken"));
    }

}
