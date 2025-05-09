package com.alvarolongueira.user.service.application.usecases;

import com.alvarolongueira.user.service.application.ports.output.UserDataOutputPort;
import com.alvarolongueira.user.service.application.ports.output.UserNotifyOutputPort;
import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.domain.exception.NotifyCreateException;
import com.alvarolongueira.user.service.domain.service.DeleteUserUseCase;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private UserDataOutputPort dataOutputPort;
    private UserNotifyOutputPort notifyOutputPort;

    @Override
    public Void process(String userId) {
        User user = dataOutputPort.findUser(userId);
        dataOutputPort.delete(userId);
        notify(user);
        return null;
    }

    private void notify(User user) {
        try {
            notifyOutputPort.notifyDelete(user);
        } catch (NotifyCreateException e) {
            log.error("Error sending notification for creation of user", e);
        }
    }
}
