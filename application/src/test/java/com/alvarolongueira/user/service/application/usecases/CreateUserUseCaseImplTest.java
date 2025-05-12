package com.alvarolongueira.user.service.application.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.alvarolongueira.user.service.application.mapper.UserMapper;
import com.alvarolongueira.user.service.application.mapper.UserMapperImpl;
import com.alvarolongueira.user.service.application.ports.output.UserDataOutputPort;
import com.alvarolongueira.user.service.application.ports.output.UserNotifyOutputPort;
import com.alvarolongueira.user.service.application.utils.ModelFactory;
import com.alvarolongueira.user.service.domain.entity.User;

import com.alvarolongueira.user.service.domain.exception.CreateUserException;
import com.alvarolongueira.user.service.domain.exception.NotifyCreateException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseImplTest {

    @Spy private final UserMapper mapper = new UserMapperImpl();
    @Mock private UserDataOutputPort dataOutputPort;
    @Mock private UserNotifyOutputPort notifyOutputPort;
    @InjectMocks private CreateUserUseCaseImpl createUserUseCase;

    @Test
    void givenRequestUserIsCreatedSuccessfully() {
        User expected = ModelFactory.getUser(ModelFactory.ID);
        when(dataOutputPort.createUser(ModelFactory.getUser(null))).thenReturn(expected);

        User user = createUserUseCase.process(ModelFactory.getUserCreationRequest());

        assertEquals(expected, user);
        verify(notifyOutputPort).notifyCreate(user);
    }

    @Test
    void givenErrorInRepositoryThrowsCreateUserException() {
        when(dataOutputPort.createUser(any())).thenThrow(RuntimeException.class);

        assertThrows(
                CreateUserException.class,
                () -> createUserUseCase.process(ModelFactory.getUserCreationRequest()));

        verifyNoInteractions(notifyOutputPort);
    }

    @Test
    void givenErrorInNotificationUserIsCreatedButIsNotNotified() {
        User expected = ModelFactory.getUser(ModelFactory.ID);
        when(dataOutputPort.createUser(ModelFactory.getUser(null))).thenReturn(expected);
        doThrow(NotifyCreateException.class).when(notifyOutputPort).notifyCreate(any());

        User user = createUserUseCase.process(ModelFactory.getUserCreationRequest());

        assertEquals(expected, user);
        verify(notifyOutputPort).notifyCreate(user);
    }
}
