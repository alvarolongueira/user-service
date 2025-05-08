package com.alvarolongueira.user.service.rest.api;

import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.domain.service.CreateUserUseCase;
import com.alvarolongueira.user.service.rest.api.handler.UseCaseHandler;
import com.alvarolongueira.user.service.rest.api.mapper.RestApiMapper;
import com.alvarolongueira.user.service.rest.api.model.CreateUser201Response;
import com.alvarolongueira.user.service.rest.api.model.CreateUserRequest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;

@Slf4j
@AllArgsConstructor
public class UserController implements UserApi {

    private RestApiMapper mapper;
    private UseCaseHandler useCaseHandler;
    private CreateUserUseCase createUserUseCase;

    @Override
    public ResponseEntity<CreateUser201Response> createUser(CreateUserRequest createUserRequest)
            throws Exception {
        CreateUserUseCase.RequestUseCase request = mapper.toCreateUserRequest(createUserRequest);
        User user = useCaseHandler.handle(createUserUseCase, request);
        return ResponseEntity.ok(mapper.toResponse(user));
    }
}
