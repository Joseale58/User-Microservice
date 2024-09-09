package com.emazon.user_service.infraestructure.output.jpa.mapper;

import com.emazon.user_service.domain.model.User;
import com.emazon.user_service.infraestructure.output.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserEntityMapper {

    @Mapping(source = "role", target = "role")
    UserEntity toUserEntity(User user);

    //@Mapping(source="roleEntity", target="role")
    User toUser(UserEntity userEntity);
}
