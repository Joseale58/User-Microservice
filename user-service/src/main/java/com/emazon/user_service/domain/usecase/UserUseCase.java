package com.emazon.user_service.domain.usecase;

import com.emazon.user_service.domain.api.IUserServicePort;
import com.emazon.user_service.domain.model.User;
import com.emazon.user_service.domain.spi.IRolePersitencePort;
import com.emazon.user_service.domain.spi.IUserPersistencePort;

public class UserUseCase implements IUserServicePort {


    private final IUserPersistencePort userPersistencePort;
    private final IRolePersitencePort rolePersitencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort, IRolePersitencePort rolePersitencePort) {
        this.userPersistencePort = userPersistencePort;
        this.rolePersitencePort = rolePersitencePort;
    }

    @Override
    public void register(User user) {
        //Validation
        this.userPersistencePort.registerUser(user);
    }

}
