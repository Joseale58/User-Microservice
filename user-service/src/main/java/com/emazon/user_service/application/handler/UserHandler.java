package com.emazon.user_service.application.handler;

import com.emazon.user_service.application.dto.RegisterDtoRequest;
import com.emazon.user_service.application.mapper.IUserRequestMapper;
import com.emazon.user_service.domain.api.IUserServicePort;
import com.emazon.user_service.domain.model.Role;
import com.emazon.user_service.domain.model.User;
import com.emazon.user_service.utils.Constants;
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
    public void registerWarehouseAux(RegisterDtoRequest registerDtoRequest) {
        User user = userRequestMapper.toUser(registerDtoRequest);
        Role role = new Role();
        role.setId(Constants.ROLE_WAREHOUSE_AUX);
        user.setRole(role);
        userServicePort.registerWarehoseAux(user);
    }

    @Override
    public void registerClient(RegisterDtoRequest registerDtoRequest) {
        User user = userRequestMapper.toUser(registerDtoRequest);
        Role role = new Role();
        role.setId(Constants.ROLE_CLIENT);
        user.setRole(role);
        userServicePort.registerClient(user);
    }
}
