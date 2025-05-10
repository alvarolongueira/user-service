package com.alvarolongueira.user.service.application.usecases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.alvarolongueira.user.service.application.ports.output.UserDataOutputPort;
import com.alvarolongueira.user.service.application.utils.ModelFactory;
import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.domain.exception.UserNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FindUserUseCaseImplTest {

    @Mock private UserDataOutputPort dataOutputPort;
    @InjectMocks private FindUserUseCaseImpl findUserUseCase;

    @Test
    void givenUserIdIsRetrievedSuccessfully() {
        User user = ModelFactory.getUser(ModelFactory.ID);
        when(dataOutputPort.findUser(ModelFactory.ID)).thenReturn(user);

        User actual = findUserUseCase.process(ModelFactory.ID);

        assertEquals(user, actual);
    }

    @Test
    void givenUserIdNoExistsThrowsUserNotFoundException() {
        when(dataOutputPort.findUser(ModelFactory.ID)).thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> findUserUseCase.process(ModelFactory.ID));

        verifyNoMoreInteractions(dataOutputPort);
    }
}
