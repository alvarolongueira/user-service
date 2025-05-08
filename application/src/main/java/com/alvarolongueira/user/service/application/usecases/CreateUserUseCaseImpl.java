package com.alvarolongueira.user.service.application.usecases;

import com.alvarolongueira.user.service.application.mapper.UserMapper;
import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.domain.service.CreateUserUseCase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    UserMapper userMapper;

    @Override
    public User process(RequestUseCase request) {
        User user = userMapper.fromCreateRequest(request);
        // TODO validate
        return null;
    }
}
