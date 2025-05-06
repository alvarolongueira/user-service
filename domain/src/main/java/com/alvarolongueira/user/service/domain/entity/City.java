package com.alvarolongueira.user.service.domain.entity;

import com.alvarolongueira.user.service.domain.vo.Id;
import com.alvarolongueira.user.service.domain.vo.State;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class City {

    private final Id id;

    private final String name;

    private final State state;

    public City(Id id, String name, State state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }
}
