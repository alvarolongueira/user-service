package com.alvarolongueira.user.service.rest.api.mapper;

import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.domain.service.CreateUserUseCase;
import com.alvarolongueira.user.service.rest.api.model.CreateUser201Response;
import com.alvarolongueira.user.service.rest.api.model.CreateUserRequest;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestApiMapper {

    CreateUserUseCase.RequestUseCase toCreateUserRequest(CreateUserRequest createUserRequest);

    CreateUser201Response toResponse(User user);
}
