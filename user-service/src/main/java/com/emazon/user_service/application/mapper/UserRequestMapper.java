package com.emazon.user_service.application.mapper;

import com.emazon.user_service.application.dto.RegisterDtoRequest;
import com.emazon.user_service.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserRequestMapper {

    public User toUser(RegisterDtoRequest registerDtoRequest){
                if(registerDtoRequest == null) {
                        return null;
                }

                User user = new User(null, null, null, null, null, null, null, null, null);

                  // Role is set to null

                // Assign values from DTO
                user.setId(registerDtoRequest.getId());
                user.setDocument(registerDtoRequest.getDocument());
                user.setName(registerDtoRequest.getName());
                user.setLastname(registerDtoRequest.getLastname());
                user.setEmail(registerDtoRequest.getEmail());
                user.setPassword(registerDtoRequest.getPassword());
                user.setCellphone(registerDtoRequest.getCellphone());
                user.setBirthdate(registerDtoRequest.getBirthdate());

                return user;
        }
}
