package com.emazon.user_service.infraestructure.output.jpa.mapper;


import com.emazon.user_service.domain.model.Role;
import com.emazon.user_service.infraestructure.output.jpa.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")

public interface IRoleEntityMapper {
    Role toRole(RoleEntity roleEntity);

}
