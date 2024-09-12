package com.emazon.user_service.infraestructure.configuration;

import com.emazon.user_service.domain.api.IAuthenticationServicePort;
import com.emazon.user_service.domain.api.IUserServicePort;
import com.emazon.user_service.domain.spi.IAuthenticationPersistencePort;
import com.emazon.user_service.domain.spi.IRolePersistencePort;
import com.emazon.user_service.domain.spi.ISecurityPersistencePort;
import com.emazon.user_service.domain.spi.IUserPersistencePort;
import com.emazon.user_service.domain.usecase.AuthenticationUseCase;
import com.emazon.user_service.domain.usecase.UserUseCase;
import com.emazon.user_service.infraestructure.output.jpa.adapter.RoleJpaAdapter;
import com.emazon.user_service.infraestructure.output.jpa.adapter.UserJpaAdapter;
import com.emazon.user_service.infraestructure.output.jpa.mapper.IRoleEntityMapper;
import com.emazon.user_service.infraestructure.output.jpa.mapper.IUserEntityMapper;
import com.emazon.user_service.infraestructure.output.jpa.repository.IRoleRepository;
import com.emazon.user_service.infraestructure.output.jpa.repository.IUserRepository;
import com.emazon.user_service.infraestructure.output.security.adapter.SecurityAdapter;
import com.emazon.user_service.infraestructure.output.security.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;
    private final JwtUtils jwtUtils;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }
    @Bean
    public IRolePersistencePort rolePersitencePort() {
        return new RoleJpaAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public ISecurityPersistencePort securityPersistencePort() {
        return new SecurityAdapter(encoder(),userPersistencePort(), jwtUtils);
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort(), rolePersitencePort(), securityPersistencePort());
    }


    @Bean
    public IAuthenticationServicePort authenticationServicePort() {
        return new AuthenticationUseCase(securityPersistencePort(),userPersistencePort());
    }


}
