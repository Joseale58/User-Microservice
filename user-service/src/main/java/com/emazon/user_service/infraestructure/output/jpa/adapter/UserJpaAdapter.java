package com.emazon.user_service.infraestructure.output.jpa.adapter;

import com.emazon.user_service.domain.model.User;
import com.emazon.user_service.domain.spi.IUserPersistencePort;
import com.emazon.user_service.infraestructure.output.jpa.entity.UserEntity;
import com.emazon.user_service.infraestructure.output.jpa.mapper.IUserEntityMapper;
import com.emazon.user_service.infraestructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor

public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public void registerUser(User user) {
        this.userRepository.save(userEntityMapper.toUserEntity(user));
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        return userEntity.map(userEntityMapper::toUser).orElse(null);
    }

    @Override
    public boolean userExistByEmail(String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        return userEntity.isPresent();
    }

    @Override
    public boolean userExistByCellPhone(String cellPhone) {
        Optional<UserEntity> userEntity = userRepository.findByCellphone(cellPhone);
        return userEntity.isPresent();
    }

    @Override
    public boolean userExistByDocument(String document) {
       Optional<UserEntity> userEntity = userRepository.findByDocument(document);
         return userEntity.isPresent();
    }
}
