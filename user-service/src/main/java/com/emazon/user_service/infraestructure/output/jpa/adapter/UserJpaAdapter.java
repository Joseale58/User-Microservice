package com.emazon.user_service.infraestructure.output.jpa.adapter;

import com.emazon.user_service.domain.model.User;
import com.emazon.user_service.domain.spi.IUserPersistencePort;
import com.emazon.user_service.infraestructure.output.jpa.entity.UserEntity;
import com.emazon.user_service.infraestructure.output.jpa.mapper.IRoleEntityMapper;
import com.emazon.user_service.infraestructure.output.jpa.mapper.IUserEntityMapper;
import com.emazon.user_service.infraestructure.output.jpa.repository.IRoleRepository;
import com.emazon.user_service.infraestructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public void registerUser(User user) {
        this.userRepository.save(userEntityMapper.toUserEntity(user));
    }

    @Override
    public boolean userExistByEmail(String email) {
        return false;
    }

    @Override
    public boolean userExistByCellPhone(String cellPhone) {
        return false;
    }

    @Override
    public boolean userExistByDocument(String document) {
        return false;
    }
}
