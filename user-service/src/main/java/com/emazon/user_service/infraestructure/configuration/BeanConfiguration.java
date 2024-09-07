package com.emazon.user_service.infraestructure.configuration;

import com.emazon.user_service.domain.api.IUserServicePort;
import com.emazon.user_service.domain.spi.IRolePersitencePort;
import com.emazon.user_service.domain.spi.IUserPersistencePort;
import com.emazon.user_service.domain.usecase.UserUseCase;
import com.emazon.user_service.infraestructure.output.jpa.adapter.RoleJpaAdapter;
import com.emazon.user_service.infraestructure.output.jpa.adapter.UserJpaAdapter;
import com.emazon.user_service.infraestructure.output.jpa.mapper.IRoleEntityMapper;
import com.emazon.user_service.infraestructure.output.jpa.mapper.IUserEntityMapper;
import com.emazon.user_service.infraestructure.output.jpa.repository.IRoleRepository;
import com.emazon.user_service.infraestructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }
    @Bean
    public IRolePersitencePort rolePersitencePort() {
        return new RoleJpaAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort(), rolePersitencePort());
    }

}
