package com.alvarolongueira.user.service.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.alvarolongueira.user.service.infrastructure.database.repository.UserRepository;
import com.alvarolongueira.user.service.infrastructure.database.repository.entity.UserEntity;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class DatabaseUtils {

    private UserRepository repository;

    public UserEntity find(String userId) {
        UserEntity entity = repository.findById(userId).orElse(null);
        assertNotNull(entity);
        return entity;
    }

    public void noExists(String userId) {
        Optional<UserEntity> entity = repository.findById(userId);
        assertTrue(entity.isEmpty());
    }
}
