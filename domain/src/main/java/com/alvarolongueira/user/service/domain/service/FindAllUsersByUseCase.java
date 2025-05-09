package com.alvarolongueira.user.service.domain.service;

import com.alvarolongueira.user.service.domain.entity.PageableUser;
import com.alvarolongueira.user.service.domain.entity.UserSearchRequest;

public interface FindAllUsersByUseCase extends UseCase<UserSearchRequest, PageableUser> {}
