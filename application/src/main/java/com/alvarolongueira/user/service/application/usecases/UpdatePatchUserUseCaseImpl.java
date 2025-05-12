package com.alvarolongueira.user.service.application.usecases;

import com.alvarolongueira.user.service.application.mapper.UserMapper;
import com.alvarolongueira.user.service.application.ports.output.UserDataOutputPort;
import com.alvarolongueira.user.service.application.ports.output.UserNotifyOutputPort;
import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.domain.entity.UserModificationRequest;
import com.alvarolongueira.user.service.domain.exception.NotifyCreateException;
import com.alvarolongueira.user.service.domain.exception.UpdateUserException;
import com.alvarolongueira.user.service.domain.exception.UserNotFoundException;
import com.alvarolongueira.user.service.domain.service.UpdatePatchUserUseCase;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UpdatePatchUserUseCaseImpl implements UpdatePatchUserUseCase {

    private UserMapper mapper;
    private UserDataOutputPort dataOutputPort;
    private UserNotifyOutputPort notifyOutputPort;

    @Override
    public User process(UserModificationRequest request) {
        User user = update(request);
        notify(user);
        return user;
    }

    private User update(UserModificationRequest request) {
        try {
            User user = mapper.toUser(request);
            return dataOutputPort.updatePatchUser(user);
        } catch (UserNotFoundException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new UpdateUserException(request.id(), e);
        }
    }

    private void notify(User user) {
        try {
            notifyOutputPort.notifyUpdate(user);
        } catch (NotifyCreateException e) {
            log.error("Error sending notification for patch update user {}", user);
        }
    }
}
