package com.alvarolongueira.user.service.application.usecases;

import com.alvarolongueira.user.service.application.mapper.UserMapper;
import com.alvarolongueira.user.service.application.ports.output.UserDataOutputPort;
import com.alvarolongueira.user.service.application.ports.output.UserNotifyOutputPort;
import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.domain.entity.UserCreationRequest;
import com.alvarolongueira.user.service.domain.exception.CreateUserException;
import com.alvarolongueira.user.service.domain.exception.NotifyCreateException;
import com.alvarolongueira.user.service.domain.service.CreateUserUseCase;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private UserMapper mapper;
    private UserDataOutputPort dataOutputPort;
    private UserNotifyOutputPort notifyOutputPort;

    @Override
    public User process(UserCreationRequest request) {
        User user = create(request);
        notify(user);
        return user;
    }

    private User create(UserCreationRequest request) {
        try {
            User user = mapper.toUser(request);
            return dataOutputPort.createUser(user);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new CreateUserException(e);
        }
    }

    private void notify(User user) {
        try {
            notifyOutputPort.notifyCreate(user);
        } catch (NotifyCreateException e) {
            log.error("Error sending notification for creation user {}", user);
        }
    }
}
