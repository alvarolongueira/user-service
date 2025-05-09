package com.alvarolongueira.user.service.application.usecases;

import com.alvarolongueira.user.service.application.ports.output.UserDataOutputPort;
import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.domain.service.FindUserUseCase;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class FindUserUseCaseImpl implements FindUserUseCase {

    private UserDataOutputPort outputPort;

    @Override
    public User process(String userId) {
        return outputPort.findUser(userId);
    }
}
