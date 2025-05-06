package com.alvarolongueira.user.service.application.ports.input;

import com.alvarolongueira.user.service.application.ports.output.CityManagementOutputPort;
import com.alvarolongueira.user.service.application.usecases.CityManagementUseCase;
import com.alvarolongueira.user.service.domain.entity.City;
import com.alvarolongueira.user.service.domain.entity.factory.CityFactory;
import com.alvarolongueira.user.service.domain.vo.Id;
import com.alvarolongueira.user.service.domain.vo.State;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class CityManagementInputPort implements CityManagementUseCase {

    @Autowired CityManagementOutputPort cityManagementOutputPort;

    @Override
    public City createCity(String name, State state) {
        var city = CityFactory.createCity(name, state);
        return cityManagementOutputPort.saveCity(city);
    }

    @Override
    public Optional<City> retrieveCity(Id id) {
        return cityManagementOutputPort.retrieveCity(id);
    }

    @Override
    public Optional<City> removeCity(Id id) {
        return cityManagementOutputPort.removeCity(id);
    }
}
