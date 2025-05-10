package com.alvarolongueira.user.service.application.usecases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.alvarolongueira.user.service.application.mapper.UserMapper;
import com.alvarolongueira.user.service.application.mapper.UserMapperImpl;
import com.alvarolongueira.user.service.application.ports.output.UserDataOutputPort;
import com.alvarolongueira.user.service.application.ports.output.UserNotifyOutputPort;
import com.alvarolongueira.user.service.application.utils.ModelFactory;
import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.domain.exception.NotifyCreateException;
import com.alvarolongueira.user.service.domain.exception.UpdateUserException;
import com.alvarolongueira.user.service.domain.exception.UserNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateUserUseCaseImplTest {

    @Spy private final UserMapper mapper = new UserMapperImpl();
    @Mock private UserDataOutputPort dataOutputPort;
    @Mock private UserNotifyOutputPort notifyOutputPort;
    @InjectMocks private UpdateUserUseCaseImpl updateUserUseCase;

    @Test
    void givenRequestUserIsUpdatedSuccessfully() {
        User expected = ModelFactory.getUser(ModelFactory.ID);
        when(dataOutputPort.updateUser(expected)).thenReturn(expected);

        User user = updateUserUseCase.process(ModelFactory.getUserModificationRequest());

        assertEquals(expected, user);
        verify(notifyOutputPort).notifyUpdate(user);
    }

    @Test
    void givenUserIdNoExistsThrowsUserNotFoundException() {
        when(dataOutputPort.updateUser(any())).thenThrow(UserNotFoundException.class);

        assertThrows(
                UserNotFoundException.class,
                () -> updateUserUseCase.process(ModelFactory.getUserModificationRequest()));

        verifyNoMoreInteractions(dataOutputPort);
        verifyNoInteractions(notifyOutputPort);
    }

    @Test
    void givenErrorInRepositoryThrowsUpdateUserException() {
        User expected = ModelFactory.getUser(ModelFactory.ID);
        when(dataOutputPort.updateUser(expected)).thenThrow(RuntimeException.class);

        assertThrows(
                UpdateUserException.class,
                () -> updateUserUseCase.process(ModelFactory.getUserModificationRequest()));

        verifyNoInteractions(notifyOutputPort);
    }

    @Test
    void givenErrorInNotificationUserIsUpdatedButIsNotNotified() {
        User expected = ModelFactory.getUser(ModelFactory.ID);
        when(dataOutputPort.updateUser(expected)).thenReturn(expected);
        doThrow(NotifyCreateException.class).when(notifyOutputPort).notifyUpdate(any());

        User user = updateUserUseCase.process(ModelFactory.getUserModificationRequest());

        assertEquals(expected, user);
        verify(notifyOutputPort).notifyUpdate(user);
    }
}
