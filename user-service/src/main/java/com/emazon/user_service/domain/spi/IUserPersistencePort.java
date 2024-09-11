package com.emazon.user_service.domain.spi;

import com.emazon.user_service.domain.model.User;

public interface IUserPersistencePort {

    void registerUser(User user);
    User getUserByEmail(String email);
    boolean userExistByEmail(String email);
    boolean userExistByCellPhone(String cellPhone);
    boolean userExistByDocument(String document);
}
