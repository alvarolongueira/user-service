package com.alvarolongueira.user.service.application.mapper;

import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.domain.service.CreateUserUseCase;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromCreateRequest(CreateUserUseCase.RequestUseCase request);
}
