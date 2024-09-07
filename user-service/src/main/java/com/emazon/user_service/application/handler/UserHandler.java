package com.emazon.user_service.application.handler;

import com.emazon.user_service.application.dto.RegisterDtoRequest;
import com.emazon.user_service.application.mapper.IUserRequestMapper;
import com.emazon.user_service.domain.api.IUserServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserHandler implements  IUserHandler{


    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;

    @Override
    public void registerUser(RegisterDtoRequest registerDtoRequest) {
       userServicePort.register(userRequestMapper.toUser(registerDtoRequest));
    }


}
