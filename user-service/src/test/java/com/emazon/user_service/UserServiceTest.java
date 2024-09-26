package com.emazon.user_service;

import com.emazon.user_service.domain.api.IUserServicePort;
import com.emazon.user_service.domain.exception.*;
import com.emazon.user_service.domain.model.Role;
import com.emazon.user_service.domain.model.User;
import com.emazon.user_service.domain.spi.ISecurityPersistencePort;
import com.emazon.user_service.domain.spi.IUserPersistencePort;
import com.emazon.user_service.domain.spi.IRolePersistencePort;
import com.emazon.user_service.domain.usecase.UserUseCase;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


public class UserServiceTest {

    private IUserPersistencePort userPersistencePort;
    private IRolePersistencePort rolePersistencePort;
    private ISecurityPersistencePort securityPersistencePort;
    private IUserServicePort userServicePort;
    private User user;

    @BeforeEach
    public void setUp() {
        userPersistencePort = Mockito.mock(IUserPersistencePort.class);
        rolePersistencePort = Mockito.mock(IRolePersistencePort.class);
        securityPersistencePort = Mockito.mock(ISecurityPersistencePort.class);
        userServicePort = new UserUseCase(userPersistencePort, rolePersistencePort, securityPersistencePort);
        user = new User(
                1L,
                "123456",
                "Nombre",
                "Apellido",
                "usuario@correo.com",
                "password123",
                "+1234567890",
                LocalDate.of(2000, 1, 1),
                new Role(1L, "aux_bodega", "aux_bodega"));
    }

    @Test
    void testRegister_NullDocument_ThrowsMissingValueException() {
        user.setDocument(null);
        assertThrows(MissingValueException.class, () -> {
            userServicePort.registerWarehoseAux(user);
        });
    }

    @Test
    void testRegister_EmptyDocument_ThrowsMissingValueException() {
        user.setDocument("");
        assertThrows(MissingValueException.class, () -> {
            userServicePort.registerWarehoseAux(user);
        });
    }
    @Test
    void testRegister_InvalidDocument_ThrowsInvalidDocumentException() {
        user.setDocument("abc123");
        assertThrows(InvalidDocumentException.class, () -> {
            userServicePort.registerWarehoseAux(user);
        });
    }

    @Test
    void testRegister_NullName_ThrowsMissingValueException() {
        user.setName(null);
        assertThrows(MissingValueException.class, () -> {
            userServicePort.registerWarehoseAux(user);
        });

    }

    @Test
    void testRegister_EmptyName_ThrowsMissingValueException() {
        user.setName("");
        assertThrows(MissingValueException.class, () -> {
            userServicePort.registerWarehoseAux(user);
        });
    }

    @Test
    void testRegister_NullLastname_ThrowsMissingValueException() {
        user.setLastname(null);
        assertThrows(MissingValueException.class, () -> {
            userServicePort.registerWarehoseAux(user);
        });
    }

    @Test
    void testRegister_EmptyLastname_ThrowsMissingValueException() {
        user.setLastname("");
        assertThrows(MissingValueException.class, () -> {
            userServicePort.registerWarehoseAux(user);
        });
    }

    @Test
    void testRegister_NullEmail_ThrowsMissingValueException() {
        user.setEmail(null);
        assertThrows(MissingValueException.class, () -> {
            userServicePort.registerWarehoseAux(user);
        });
    }

    @Test
    void testRegister_EmptyEmail_ThrowsMissingValueException() {
        user.setEmail("");
        assertThrows(MissingValueException.class, () -> {
            userServicePort.registerWarehoseAux(user);
        });
    }

    @Test
    void testRegister_InvalidEmail_ThrowsInvalidEmailException() {
        user.setEmail("correo-invalido");
        assertThrows(InvalidEmailException.class, () -> {
            userServicePort.registerWarehoseAux(user);
        });
    }

    @Test
    void testRegister_NullPassword_ThrowsMissingValueException() {
        user.setPassword(null);
        assertThrows(MissingValueException.class, () -> {
            userServicePort.registerWarehoseAux(user);
        });
    }

    @Test
    void testRegister_EmptyPassword_ThrowsMissingValueException() {
        user.setPassword("");
        assertThrows(MissingValueException.class, () -> {
            userServicePort.registerWarehoseAux(user);
        });
    }

    @Test
    void testRegister_NullCellphone_ThrowsMissingValueException() {
        user.setCellphone(null);
        assertThrows(MissingValueException.class, () -> {
            userServicePort.registerWarehoseAux(user);
        });
    }

    @Test
    void testRegister_EmptyCellphone_ThrowsMissingValueException() {
        user.setCellphone("");
        assertThrows(MissingValueException.class, () -> {
            userServicePort.registerWarehoseAux(user);
        });
    }

    @Test
    void testRegister_InvalidPhone_ThrowsInvalidPhoneException() {
        user.setCellphone("123456789012345");
        assertThrows(InvalidPhoneException.class, () -> {
            userServicePort.registerWarehoseAux(user);
        });
    }

    @Test
    void testRegister_NullBirthdate_ThrowsMissingValueException() {
        user.setBirthdate(null);
        assertThrows(MissingValueException.class, () -> {
            userServicePort.registerWarehoseAux(user);
        });
    }

    @Test
    void testRegister_MinorUser_ThrowsMinorException() {
        user.setBirthdate(LocalDate.now().minusYears(17));
        assertThrows(MinorException.class, () -> {
            userServicePort.registerWarehoseAux(user);
        });
    }

    @Test
    void testRegister_NullRole_ThrowsMissingValueException() {
        user.setRole(null);
        assertThrows(MissingValueException.class, () -> {
            userServicePort.registerWarehoseAux(user);
        });
    }

    @Test
    void testRegister_RoleNotFound_ThrowsRoleNotFoundException() {
        when(rolePersistencePort.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(RoleNotFoundException.class, () -> {
            userServicePort.registerWarehoseAux(user);
        });
    }

    @Test
    void testRegister_EmailAlreadyExists_ThrowsEmailAlreadyExistsException() {
        when(userPersistencePort.userExistByEmail(anyString())).thenReturn(true);
        assertThrows(EmailAlreadyExistsException.class, () -> {
            userServicePort.registerWarehoseAux(user);
        });}

    @Test
    void testRegister_DocumentAlreadyExists_ThrowsDocumentAlreadyExistsException() {
        when(userPersistencePort.userExistByDocument(anyString())).thenReturn(true);
        assertThrows(DocumentAlreadyExistsException.class, () -> {
            userServicePort.registerWarehoseAux(user);
        });
    }

    @Test
    void testRegister_CellphoneAlreadyExists_ThrowsCellPhoneAlreadyExistsException() {
        when(userPersistencePort.userExistByCellPhone(anyString())).thenReturn(true);
        assertThrows(CellPhoneAlreadyExistsException.class, () -> {
            userServicePort.registerWarehoseAux(user);
        });
    }

    @Test
    void testRegister_ValidUser_RegistersSuccessfully() {
        when(rolePersistencePort.findById(anyLong())).thenReturn(Optional.of(new Role(1L, "aux_bodega", "Auxiliar de bodega")));
        when(securityPersistencePort.encryptPassword(anyString())).thenReturn("encryptedPassword");
        when(userPersistencePort.userExistByEmail(anyString())).thenReturn(false);
        when(userPersistencePort.userExistByDocument(anyString())).thenReturn(false);
        when(userPersistencePort.userExistByCellPhone(anyString())).thenReturn(false);
        userServicePort.registerWarehoseAux(user);
        verify(userPersistencePort, times(1)).registerUser(user);
    }


}
