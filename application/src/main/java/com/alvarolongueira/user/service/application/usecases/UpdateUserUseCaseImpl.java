package com.alvarolongueira.user.service.application.usecases;

import com.alvarolongueira.user.service.application.mapper.UserMapper;
import com.alvarolongueira.user.service.application.ports.output.UserDataOutputPort;
import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.domain.entity.UserModificationRequest;
import com.alvarolongueira.user.service.domain.exception.UpdateUserException;
import com.alvarolongueira.user.service.domain.exception.UserNotFoundException;
import com.alvarolongueira.user.service.domain.service.UpdateUserUseCase;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private UserMapper mapper;
    private UserDataOutputPort outputPort;

    @Override
    public User process(UserModificationRequest request) {
        User user = mapper.toUser(request);
        try {
            return outputPort.updateUser(user);
        } catch (UserNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new UpdateUserException();
        }
    }
}
