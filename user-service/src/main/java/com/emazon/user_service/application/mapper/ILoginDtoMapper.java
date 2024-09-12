package com.emazon.user_service.application.mapper;

import com.emazon.user_service.application.dto.LoginDtoRequest;
import com.emazon.user_service.application.dto.LoginDtoResponse;
import com.emazon.user_service.domain.model.Login;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ILoginDtoMapper {
    //LoginDto to Login
    Login toLogin(LoginDtoRequest loginDtoRequest);

    LoginDtoResponse toLoginDtoResponse(String login);
}
