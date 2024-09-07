package com.emazon.user_service.infraestructure.output.jpa.adapter;

import com.emazon.user_service.domain.model.Role;
import com.emazon.user_service.domain.spi.IRolePersitencePort;
import com.emazon.user_service.infraestructure.output.jpa.mapper.IRoleEntityMapper;
import com.emazon.user_service.infraestructure.output.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersitencePort {

    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    @Override
    public Optional<Role> findByName(String name) {
        return Optional.empty();
    }

}
