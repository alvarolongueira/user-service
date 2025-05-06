package com.alvarolongueira.user.service.application.usecases;

import com.alvarolongueira.user.service.domain.entity.City;
import com.alvarolongueira.user.service.domain.vo.Id;
import com.alvarolongueira.user.service.domain.vo.State;

import java.util.Optional;

public interface CityManagementUseCase {

    City createCity(String name, State state);

    Optional<City> retrieveCity(Id id);

    Optional<City> removeCity(Id id);
}
