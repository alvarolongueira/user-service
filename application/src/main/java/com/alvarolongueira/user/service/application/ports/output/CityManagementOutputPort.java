package com.alvarolongueira.user.service.application.ports.output;

import com.alvarolongueira.user.service.domain.entity.City;
import com.alvarolongueira.user.service.domain.vo.Id;

import java.util.Optional;

public interface CityManagementOutputPort {

    Optional<City> retrieveCity(Id id);

    Optional<City> removeCity(Id id);

    City saveCity(City city);
}
