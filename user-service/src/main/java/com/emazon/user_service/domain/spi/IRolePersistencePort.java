package com.emazon.user_service.domain.spi;

import com.emazon.user_service.domain.model.Role;

import java.util.*;

public interface IRolePersistencePort {
    Optional<Role> findByName(String name);
    Optional<Role> findById(Long id);
}
