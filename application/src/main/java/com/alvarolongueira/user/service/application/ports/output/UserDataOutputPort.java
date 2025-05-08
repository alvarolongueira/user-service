package com.alvarolongueira.user.service.application.ports.output;

import com.alvarolongueira.user.service.domain.entity.User;

public interface UserDataOutputPort {

    User findUser(String userId);

    User createUser(User user);
}
