package com.alvarolongueira.user.service.domain.entity.factory;

import com.alvarolongueira.user.service.domain.entity.City;
import com.alvarolongueira.user.service.domain.vo.Id;
import com.alvarolongueira.user.service.domain.vo.State;

public class CityFactory {

    public static City createCity(String name, State state) {
        return new City(Id.withoutId(), name, state);
    }

    public static City createCity(String uuid, String name, String state) {
        return new City(Id.withId(uuid), name, State.valueOf(state));
    }
}
