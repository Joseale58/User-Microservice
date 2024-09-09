package com.emazon.user_service.infraestructure.output.jpa.repository;

import com.emazon.user_service.infraestructure.output.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String name);
}
