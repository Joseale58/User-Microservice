package com.emazon.user_service.infraestructure.output.security.adapter;

import com.emazon.user_service.domain.model.Login;
import com.emazon.user_service.domain.model.User;
import com.emazon.user_service.domain.spi.ISecurityPersistencePort;
import com.emazon.user_service.domain.spi.IUserPersistencePort;
import com.emazon.user_service.infraestructure.output.security.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class SecurityAdapter implements ISecurityPersistencePort {


    private final PasswordEncoder passwordEncoder;
    private final IUserPersistencePort userPersistencePort;
    private final JwtUtils jwtUtils;

    @Override
    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public String authenticate(Login login) {
        User userDetails = userPersistencePort.getUserByEmail(login.getEmail());
        if(!passwordEncoder.matches(login.getPassword(),userDetails.getPassword())){
            throw new BadCredentialsException("Credenciales incorrectas");
        }
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(userDetails.getRole().getName()));
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getEmail(),userDetails.getPassword(),authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.createToken(authentication);
        return token;
    }


}
