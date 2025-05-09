package com.alvarolongueira.user.service.infrastructure.rest.api;

import com.alvarolongueira.user.service.domain.entity.PageableUser;
import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.domain.entity.UserCreationRequest;
import com.alvarolongueira.user.service.domain.entity.UserModificationRequest;
import com.alvarolongueira.user.service.domain.entity.UserSearchRequest;
import com.alvarolongueira.user.service.domain.service.CreateUserUseCase;
import com.alvarolongueira.user.service.domain.service.DeleteUserUseCase;
import com.alvarolongueira.user.service.domain.service.FindAllUsersByUseCase;
import com.alvarolongueira.user.service.domain.service.FindUserUseCase;
import com.alvarolongueira.user.service.domain.service.UpdatePatchUserUseCase;
import com.alvarolongueira.user.service.domain.service.UpdateUserUseCase;
import com.alvarolongueira.user.service.infrastructure.rest.api.handler.UseCaseHandler;
import com.alvarolongueira.user.service.infrastructure.rest.api.mapper.RestApiMapper;
import com.alvarolongueira.user.service.rest.api.UserApi;
import com.alvarolongueira.user.service.rest.api.model.CreateUser201Response;
import com.alvarolongueira.user.service.rest.api.model.CreateUserRequest;
import com.alvarolongueira.user.service.rest.api.model.GetUsersBy200Response;
import com.alvarolongueira.user.service.rest.api.model.UserModel;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class UserController implements UserApi {

    private RestApiMapper mapper;
    private UseCaseHandler useCaseHandler;
    private FindAllUsersByUseCase findAllUsersByUseCase;
    private FindUserUseCase findUserUseCase;
    private CreateUserUseCase createUserUseCase;
    private UpdateUserUseCase updateUserUseCase;
    private UpdatePatchUserUseCase updatePatchUserUseCase;
    private DeleteUserUseCase deleteUserUseCase;

    @Override
    public ResponseEntity<GetUsersBy200Response> getUsersBy(
            Integer page, Integer size, String firstName, String lastName, String country)
            throws Exception {
        UserSearchRequest domainRequest =
                UserSearchRequest.builder()
                        .page(page)
                        .size(size)
                        .firstName(firstName)
                        .lastName(lastName)
                        .country(country)
                        .build();
        PageableUser pageableUser = useCaseHandler.handle(findAllUsersByUseCase, domainRequest);
        return ResponseEntity.ok(mapper.toGetPageableUserResponse(pageableUser));
    }

    @Override
    public ResponseEntity<UserModel> getUserById(String userId) throws Exception {
        User user = useCaseHandler.handle(findUserUseCase, userId);
        return ResponseEntity.ok(mapper.toGetUserResponse(user));
    }

    @Override
    public ResponseEntity<CreateUser201Response> createUser(CreateUserRequest createUserRequest)
            throws Exception {
        UserCreationRequest domainRequest = mapper.toCreation(createUserRequest);
        User user = useCaseHandler.handle(createUserUseCase, domainRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toCreateUserResponse(user));
    }

    @Override
    public ResponseEntity<UserModel> updateUser(String userId, CreateUserRequest createUserRequest)
            throws Exception {
        UserModificationRequest domainRequest = mapper.toModification(userId, createUserRequest);
        User user = useCaseHandler.handle(updateUserUseCase, domainRequest);
        return ResponseEntity.ok(mapper.toGetUserResponse(user));
    }

    @Override
    public ResponseEntity<UserModel> updatePatchUser(
            String userId, CreateUserRequest createUserRequest) throws Exception {
        UserModificationRequest domainRequest = mapper.toModification(userId, createUserRequest);
        User user = useCaseHandler.handle(updatePatchUserUseCase, domainRequest);
        return ResponseEntity.ok(mapper.toGetUserResponse(user));
    }

    @Override
    public ResponseEntity<Void> deleteUser(String userId) throws Exception {
        useCaseHandler.handle(deleteUserUseCase, userId);
        return ResponseEntity.ok(null);
    }
}
