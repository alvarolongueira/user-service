package com.alvarolongueira.user.service.application.usecases;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.alvarolongueira.user.service.application.ports.output.UserDataOutputPort;
import com.alvarolongueira.user.service.application.ports.output.UserNotifyOutputPort;
import com.alvarolongueira.user.service.application.utils.ModelFactory;
import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.domain.exception.NotifyCreateException;
import com.alvarolongueira.user.service.domain.exception.UserNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteUserUseCaseImplTest {

    @Mock private UserDataOutputPort dataOutputPort;
    @Mock private UserNotifyOutputPort notifyOutputPort;
    @InjectMocks private DeleteUserUseCaseImpl deleteUserUseCase;

    @Test
    void givenUserIdIsDeletedSuccessfully() {
        User user = ModelFactory.getUser(ModelFactory.ID);
        when(dataOutputPort.findUser(ModelFactory.ID)).thenReturn(user);

        deleteUserUseCase.process(ModelFactory.ID);

        verify(dataOutputPort).delete(ModelFactory.ID);
        verify(notifyOutputPort).notifyDelete(user);
    }

    @Test
    void givenUserIdNoExistsThrowsUserNotFoundException() {
        when(dataOutputPort.findUser(ModelFactory.ID)).thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> deleteUserUseCase.process(ModelFactory.ID));

        verifyNoMoreInteractions(dataOutputPort);
        verifyNoInteractions(notifyOutputPort);
    }

    @Test
    void givenErrorInNotificationUserIsDeletedButIsNotNotified() {
        User user = ModelFactory.getUser(ModelFactory.ID);
        when(dataOutputPort.findUser(ModelFactory.ID)).thenReturn(user);
        doThrow(NotifyCreateException.class).when(notifyOutputPort).notifyDelete(user);

        deleteUserUseCase.process(ModelFactory.ID);

        verify(notifyOutputPort).notifyDelete(user);
    }
}
