package com.alvarolongueira.user.service.rest.api.mapper;

import com.alvarolongueira.user.service.domain.entity.PageableUser;
import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.domain.entity.UserCreationRequest;
import com.alvarolongueira.user.service.domain.entity.UserModificationRequest;
import com.alvarolongueira.user.service.domain.mapper.DateMapper;
import com.alvarolongueira.user.service.rest.api.model.CreateUser201Response;
import com.alvarolongueira.user.service.rest.api.model.CreateUserRequest;
import com.alvarolongueira.user.service.rest.api.model.GetUsersBy200Response;
import com.alvarolongueira.user.service.rest.api.model.UserModel;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {DateMapper.class})
public interface RestApiMapper {

    UserCreationRequest toCreation(CreateUserRequest createUserRequest);

    UserModificationRequest toModification(String id, CreateUserRequest createUserRequest);

    UserModel toGetUserResponse(User user);

    @Mapping(source = "userList", target = "content")
    GetUsersBy200Response toGetPageableUserResponse(PageableUser pageableUser);

    CreateUser201Response toCreateUserResponse(User user);
}
