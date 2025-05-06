package com.alvarolongueira.user.service.application.ports.input;

import static org.mockito.Mockito.when;

import com.alvarolongueira.user.service.application.ports.output.CityManagementOutputPort;
import com.alvarolongueira.user.service.domain.vo.State;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CityManagementInputPortTest {

    @Mock CityManagementOutputPort cityManagementOutputPort;

    @InjectMocks CityManagementInputPort cityManagementInputPort;

    @Test
    void shouldCreateCityWithSuccess() {

        var cityName = "Rio de Janeiro";
        var state = State.RJ;
        when(cityManagementOutputPort.saveCity(Mockito.any()))
                .thenAnswer(
                        input -> {
                            return input.getArgument(0);
                        });

        var cityCreated = cityManagementInputPort.createCity(cityName, state);

        Assertions.assertEquals(cityName, cityCreated.getName());
        Assertions.assertEquals(state, cityCreated.getState());
    }
}
