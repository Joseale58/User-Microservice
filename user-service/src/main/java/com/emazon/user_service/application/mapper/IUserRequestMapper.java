package com.emazon.user_service.application.mapper;


import com.emazon.user_service.application.dto.RegisterDtoRequest;
import com.emazon.user_service.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserRequestMapper {

        //RegisterDtoRequest to User
        User toUser(RegisterDtoRequest registerDtoRequest);



}
