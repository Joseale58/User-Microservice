package com.emazon.user_service.infraestructure.output.jpa.repository;

import com.emazon.user_service.infraestructure.output.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByDocument(String document);
    Optional<UserEntity> findByCellphone(String cellphone);
}
