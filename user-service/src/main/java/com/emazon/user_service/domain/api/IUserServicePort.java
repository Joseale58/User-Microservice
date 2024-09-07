package com.emazon.user_service.domain.api;

import com.emazon.user_service.domain.model.User;

public interface IUserServicePort {

    void register(User user);
}
