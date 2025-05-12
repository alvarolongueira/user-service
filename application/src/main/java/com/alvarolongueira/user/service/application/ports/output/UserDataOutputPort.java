package com.alvarolongueira.user.service.application.ports.output;

import com.alvarolongueira.user.service.domain.entity.PageableUser;
import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.domain.entity.UserSearchRequest;

public interface UserDataOutputPort {

    PageableUser findPageableUserList(UserSearchRequest request);

    User findUser(String userId);

    User createUser(User user);

    User updateUser(User user);

    User updatePatchUser(User user);

    void delete(String userId);
}
