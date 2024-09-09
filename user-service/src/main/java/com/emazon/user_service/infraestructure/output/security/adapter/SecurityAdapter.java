package com.emazon.user_service.infraestructure.output.security.adapter;

import com.emazon.user_service.domain.spi.ISecurityPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class SecurityAdapter implements ISecurityPersistencePort {


    private final PasswordEncoder passwordEncoder;

    @Override
    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
