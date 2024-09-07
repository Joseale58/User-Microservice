package com.emazon.user_service.domain.spi;

import com.emazon.user_service.domain.model.Role;

import java.util.*;

public interface IRolePersitencePort {
    Optional<Role> findByName(String name);
}
