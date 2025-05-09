package com.alvarolongueira.user.service.application.usecases;

import com.alvarolongueira.user.service.application.ports.output.UserDataOutputPort;
import com.alvarolongueira.user.service.domain.service.DeleteUserUseCase;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private UserDataOutputPort outputPort;

    @Override
    public Void process(String userId) {
        outputPort.delete(userId);
        return null;
    }
}
