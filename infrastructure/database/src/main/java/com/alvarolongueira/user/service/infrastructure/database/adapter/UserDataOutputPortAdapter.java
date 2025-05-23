package com.alvarolongueira.user.service.infrastructure.database.adapter;

import com.alvarolongueira.user.service.application.ports.output.UserDataOutputPort;
import com.alvarolongueira.user.service.domain.entity.PageableUser;
import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.domain.entity.UserSearchRequest;
import com.alvarolongueira.user.service.domain.exception.UserNotFoundException;
import com.alvarolongueira.user.service.infrastructure.database.adapter.mapper.EntityMapper;
import com.alvarolongueira.user.service.infrastructure.database.repository.UserRepository;
import com.alvarolongueira.user.service.infrastructure.database.repository.entity.UserEntity;

import jakarta.transaction.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class UserDataOutputPortAdapter implements UserDataOutputPort {

    private final UserRepository repository;
    private EntityMapper mapper;

    @Override
    public PageableUser findPageableUserList(UserSearchRequest request) {
        log.info("Searching users with request {}", request);
        Pageable pageable = PageRequest.of(request.page(), request.size());
        UserEntity exampleEntity = new UserEntity();
        exampleEntity.setFirstName(request.firstName());
        exampleEntity.setLastName(request.lastName());
        exampleEntity.setCountry(request.country());

        Example<UserEntity> example = Example.of(exampleEntity);
        Page<UserEntity> page = repository.findAll(example, pageable);
        return mapper.toPage(page);
    }

    @Override
    public User findUser(String userId) {
        log.info("Searching user with id {}", userId);
        UserEntity entity = findUserEntity(userId);
        return mapper.toUser(entity);
    }

    @Override
    public User createUser(User user) {
        log.info("Creating new user {}", user);
        UserEntity entity = repository.save(mapper.toUserEntity(user));
        return mapper.toUser(entity);
    }

    @Override
    public User updateUser(User user) {
        log.info("Updating user {}", user);
        UserEntity oldEntity = findUserEntity(user.getId());
        UserEntity newEntity = mapper.toUserEntityUpdate(user, oldEntity);
        UserEntity savedEntity = this.repository.save(newEntity);
        return mapper.toUser(savedEntity);
    }

    @Override
    public User updatePatchUser(User user) {
        log.info("Updating patch user {}", user);
        UserEntity oldEntity = findUserEntity(user.getId());
        UserEntity newEntity = mapper.toUserEntityPatch(user, oldEntity);
        this.repository.save(newEntity);
        return mapper.toUser(newEntity);
    }

    @Override
    public void delete(String userId) {
        log.info("Deleting user {}", userId);
        repository.deleteById(userId);
    }

    private UserEntity findUserEntity(String userId) {
        return repository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }
}
