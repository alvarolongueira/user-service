package com.alvarolongueira.user.service.domain.service;

import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.domain.entity.UserModificationRequest;

public interface UpdatePatchUserUseCase extends UseCase<UserModificationRequest, User> {}
