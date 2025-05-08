package com.alvarolongueira.user.service.application.usecases;

import com.alvarolongueira.user.service.application.mapper.UserMapper;
import com.alvarolongueira.user.service.application.ports.output.UserDataOutputPort;
import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.domain.service.CreateUserUseCase;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private UserMapper mapper;
    private UserDataOutputPort outputPort;

    @Override
    public User process(RequestUseCase request) {
        User user = mapper.fromCreateRequest(request);
        // TODO validate
        // TODO duplicates
        return outputPort.createUser(user);
    }
}
