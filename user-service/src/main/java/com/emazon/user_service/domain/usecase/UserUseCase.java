package com.emazon.user_service.domain.usecase;

import com.emazon.user_service.domain.api.IUserServicePort;
import com.emazon.user_service.domain.exception.*;
import com.emazon.user_service.domain.model.Role;
import com.emazon.user_service.domain.model.User;
import com.emazon.user_service.domain.spi.IRolePersistencePort;
import com.emazon.user_service.domain.spi.ISecurityPersistencePort;
import com.emazon.user_service.domain.spi.IUserPersistencePort;
import com.emazon.user_service.utils.Constants;

import java.time.Period;

public class UserUseCase implements IUserServicePort {


    private final IUserPersistencePort userPersistencePort;
    private final IRolePersistencePort rolePersitencePort;
    private final ISecurityPersistencePort securityPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort, IRolePersistencePort rolePersitencePort, ISecurityPersistencePort securityPersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.rolePersitencePort = rolePersitencePort;
        this.securityPersistencePort = securityPersistencePort;
    }

    @Override
    public void register(User user) {
        if(user.getDocument() == null || user.getDocument().isEmpty()) {
            throw new MissingValueException(Constants.MISSING_DOCUMENT_EXCEPTION);
        }
        if (!user.getDocument().matches("\\d+")) {
            throw new InvalidDocumentException(Constants.INVALID_DOCUMENT_EXCEPTION);
        }
        if(user.getName() == null || user.getName().isEmpty()) {
            throw new MissingValueException(Constants.MISSING_NAME_EXCEPTION);
        }
        if(user.getLastname() == null || user.getLastname().isEmpty()) {
            throw new MissingValueException(Constants.MISSING_LASTNAME_EXCEPTION);
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new MissingValueException(Constants.MISSING_EMAIL_EXCEPTION);
        }
        if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new InvalidEmailException(Constants.INVALID_EMAIL_EXCEPTION);
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new MissingValueException(Constants.MISSING_PASSWORD_EXCEPTION);
        }
        if (user.getCellphone()== null || user.getCellphone().isEmpty()) {
            throw new MissingValueException(Constants.MISSING_CELLPHONE_EXCEPTION);
        }
        if (!user.getCellphone().matches("^\\+?\\d{1,12}$")) {
            throw new InvalidPhoneException(Constants.INVALID_CELLPHONE_EXCEPTION);
        }
        if(user.getBirthdate() == null) {
            throw new MissingValueException(Constants.MISSING_DOB_EXCEPTION);
        }
        if (user.getRole() == null) {
            throw new MissingValueException(Constants.MISSING_ROLE_EXCEPTION);
        }

        if(userPersistencePort.userExistByEmail(user.getEmail())){
            throw new EmailAlreadyExistsException(Constants.EMAIL_ALREADY_EXISTS_EXCEPTION);
        }

        if(userPersistencePort.userExistByDocument(user.getDocument())){
            throw new DocumentAlreadyExistsException(Constants.DOCUMENT_ALREADY_EXISTS_EXCEPTION);
        }

        if(userPersistencePort.userExistByCellPhone(user.getCellphone())){
            throw new CellPhoneAlreadyExistsException(Constants.CELLPHONE_ALREADY_EXISTS_EXCEPTION);
        }

        boolean isAdult = Period.between(user.getBirthdate(), java.time.LocalDate.now()).getYears() >= Constants.MIN_LEGAL_AGE;

        if(!isAdult){
            throw new MinorException(Constants.MINOR_EXCEPTION);
        }

        Role role = rolePersitencePort.findById(user.getRole().getId()).orElseThrow(() -> new RoleNotFoundException(Constants.ROLE_DOES_NOT_EXISTS_EXCEPTION));

        user.setRole(role);

        user.setPassword(securityPersistencePort.encryptPassword(user.getPassword()));

        this.userPersistencePort.registerUser(user);
    }



}
