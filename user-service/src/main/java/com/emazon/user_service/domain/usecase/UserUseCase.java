package com.emazon.user_service.domain.usecase;

import ch.qos.logback.classic.encoder.JsonEncoder;
import com.emazon.user_service.domain.api.IUserServicePort;
import com.emazon.user_service.domain.exception.*;
import com.emazon.user_service.domain.model.Role;
import com.emazon.user_service.domain.model.User;
import com.emazon.user_service.domain.spi.IRolePersitencePort;
import com.emazon.user_service.domain.spi.ISecurityPersistencePort;
import com.emazon.user_service.domain.spi.IUserPersistencePort;

import java.time.Period;

public class UserUseCase implements IUserServicePort {


    private final IUserPersistencePort userPersistencePort;
    private final IRolePersitencePort rolePersitencePort;
    private final ISecurityPersistencePort securityPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort, IRolePersitencePort rolePersitencePort, ISecurityPersistencePort securityPersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.rolePersitencePort = rolePersitencePort;
        this.securityPersistencePort = securityPersistencePort;
    }

    @Override
    public void register(User user) {
        if(user.getDocument() == null || user.getDocument().isEmpty()) {
            throw new MissingValueException("documento");
        }
        if (!user.getDocument().matches("\\d+")) {
            throw new InvalidDocumentException("El documento debe ser numérico");
        }
        if(user.getName() == null || user.getName().isEmpty()) {
            throw new MissingValueException("nombre");
        }
        if(user.getLastname() == null || user.getLastname().isEmpty()) {
            throw new MissingValueException("apellido");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new MissingValueException("email");
        }
        if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new InvalidEmailException("El formato de email es inválido");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new MissingValueException("password");
        }
        if (user.getCellphone()== null || user.getCellphone().isEmpty()) {
            throw new MissingValueException("celular");
        }
        if (!user.getCellphone().matches("^\\+?\\d{1,12}$")) {
            throw new InvalidPhoneException("El teléfono debe contener máximo 13 caracteres y puede contener el símbolo +");
        }
        if(user.getBirthdate() == null) {
            throw new MissingValueException("fecha de nacimiento");
        }
        if (user.getRole() == null) {
            throw new MissingValueException("rol");
        }

        if(userPersistencePort.userExistByEmail(user.getEmail())){
            throw new EmailAlreadyExistsException("Ya existe un usuario con este correo:" + user.getEmail());
        }

        if(userPersistencePort.userExistByDocument(user.getDocument())){
            throw new DocumentAlreadyExistsException("Ya existe un usuario con este documento:" +user.getDocument());
        }

        if(userPersistencePort.userExistByCellPhone(user.getCellphone())){
            throw new CellPhoneAlreadyExistsException("Ya existe un usuario con este celular:" +user.getCellphone());
        }

        boolean isAdult = Period.between(user.getBirthdate(), java.time.LocalDate.now()).getYears() >= 18;

        if(!isAdult){
            throw new MinorException("El usuario debe ser mayor de edad");
        }

        Role role = rolePersitencePort.findById(user.getRole().getId()).orElseThrow(() -> new RoleNotFoundException(user.getRole().getName()));

        user.setRole(role);

        user.setPassword(securityPersistencePort.encryptPassword(user.getPassword()));

        this.userPersistencePort.registerUser(user);
    }



}
