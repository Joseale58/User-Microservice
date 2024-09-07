package com.emazon.user_service.application.mapper;


import com.emazon.user_service.application.dto.RegisterDtoRequest;
import com.emazon.user_service.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserRequestMapper {

        //RegisterDtoRequest to User
        User toUser(RegisterDtoRequest registerDtoRequest);
}
