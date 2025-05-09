package com.alvarolongueira.user.service.domain.service;

import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.domain.entity.UserCreationRequest;

public interface CreateUserUseCase extends UseCase<UserCreationRequest, User> {}
