package com.emazon.user_service.application.handler;

import com.emazon.user_service.application.dto.LoginDtoRequest;
import com.emazon.user_service.application.dto.LoginDtoResponse;
import com.emazon.user_service.application.mapper.ILoginDtoMapper;
import com.emazon.user_service.application.mapper.LoginDtoResponseManualMapper;
import com.emazon.user_service.domain.api.IAuthenticationServicePort;
import com.emazon.user_service.domain.model.Login;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationHandler implements IAuthenticationHandler {

    private final IAuthenticationServicePort authenticationServicePort;
    private final ILoginDtoMapper loginDtoMapper;
    private final LoginDtoResponseManualMapper loginDtoResponseManualMapper;


    @Override
    public LoginDtoResponse authenticate(LoginDtoRequest loginDtoRequest) {
        Login login = loginDtoMapper.toLogin(loginDtoRequest);
        return loginDtoResponseManualMapper.toLoginDtoResponse(authenticationServicePort.login(login));
    }
}
