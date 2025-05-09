package com.alvarolongueira.user.service.utils;

import com.alvarolongueira.user.service.infrastructure.database.repository.UserRepository;
import com.alvarolongueira.user.service.infrastructure.database.repository.entity.UserEntity;

import lombok.AllArgsConstructor;

import org.junit.jupiter.api.Assertions;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DatabaseUtils {

    private UserRepository repository;

    public UserEntity find(String userId) {
        UserEntity entity = repository.findById(userId).orElse(null);
        Assertions.assertNotNull(entity);
        return entity;
    }
}
