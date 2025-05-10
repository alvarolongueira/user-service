package com.alvarolongueira.user.service.application.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.alvarolongueira.user.service.application.utils.ModelFactory;
import com.alvarolongueira.user.service.domain.entity.User;

import org.junit.jupiter.api.Test;

class UserMapperTest {

    private final UserMapper mapper = new UserMapperImpl();

    @Test
    void toUserFromCreationRequest() {
        User actual = mapper.toUser(ModelFactory.getUserCreationRequest());
        User expected = ModelFactory.getUser(null);
        assertEquals(expected, actual);
    }

    @Test
    void toUserFromModificationRequest() {
        User actual = mapper.toUser(ModelFactory.getUserModificationRequest());
        User expected = ModelFactory.getUser(ModelFactory.ID);
        assertEquals(expected, actual);
    }
}
