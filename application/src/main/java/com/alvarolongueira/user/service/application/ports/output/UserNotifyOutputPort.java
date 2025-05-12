package com.alvarolongueira.user.service.application.ports.output;

import com.alvarolongueira.user.service.domain.entity.User;

public interface UserNotifyOutputPort {

    void notifyCreate(User user);

    void notifyUpdate(User user);

    void notifyDelete(User user);
}
