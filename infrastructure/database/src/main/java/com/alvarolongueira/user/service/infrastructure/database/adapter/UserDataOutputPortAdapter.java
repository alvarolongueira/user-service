package com.alvarolongueira.user.service.infrastructure.database.adapter;

import com.alvarolongueira.user.service.application.ports.output.UserDataOutputPort;
import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.domain.exception.UserNotFoundException;
import com.alvarolongueira.user.service.infrastructure.database.adapter.mapper.EntityMapper;
import com.alvarolongueira.user.service.infrastructure.database.repository.UserRepository;
import com.alvarolongueira.user.service.infrastructure.database.repository.entity.UserEntity;

import jakarta.transaction.Transactional;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class UserDataOutputPortAdapter implements UserDataOutputPort {

    private final UserRepository userRepository;
    private EntityMapper mapper;

    @Override
    public User findUser(String userId) {
        UserEntity entity =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new UserNotFoundException(userId));
        return mapper.toUser(entity);
    }

    @Override
    public User createUser(User user) {
        UserEntity entity = userRepository.save(mapper.toUserEntity(user));
        return mapper.toUser(entity);
    }
}
