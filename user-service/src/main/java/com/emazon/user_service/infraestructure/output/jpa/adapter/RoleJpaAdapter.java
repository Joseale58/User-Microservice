package com.emazon.user_service.infraestructure.output.jpa.adapter;

import com.emazon.user_service.domain.model.Role;
import com.emazon.user_service.domain.spi.IRolePersistencePort;
import com.emazon.user_service.infraestructure.output.jpa.entity.RoleEntity;
import com.emazon.user_service.infraestructure.output.jpa.mapper.IRoleEntityMapper;
import com.emazon.user_service.infraestructure.output.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {

    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    @Override
    public Optional<Role> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<Role> findById(Long id) {
        Optional<RoleEntity> role = roleRepository.findById(id);
        if(role.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(roleEntityMapper.toRole(role.get()));
    }

}
