package com.alvarolongueira.user.service.application.utils;

import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.domain.entity.UserCreationRequest;
import com.alvarolongueira.user.service.domain.entity.UserModificationRequest;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ModelFactory {
    public static final String ID = "ID_FIELD";
    public static final String FIRST_NAME = "FIRS_NAME_FIELD";
    public static final String LAST_NAME = "LAST_NAME_FIELD";
    public static final String NICKNAME = "NICKNAME_FIELD";
    public static final String PASSWORD = "PASSWORD_FIELD";
    public static final String EMAIL = "EMAIL_FIELD";
    public static final String COUNTRY = "COUNTRY_FIELD";

    public static User getUser(String userId) {
        return new User(
                userId, FIRST_NAME, LAST_NAME, NICKNAME, PASSWORD, EMAIL, COUNTRY, null, null);
    }

    public static UserCreationRequest getUserCreationRequest() {
        return new UserCreationRequest(FIRST_NAME, LAST_NAME, NICKNAME, PASSWORD, EMAIL, COUNTRY);
    }

    public static UserModificationRequest getUserModificationRequest() {
        return new UserModificationRequest(
                ID, FIRST_NAME, LAST_NAME, NICKNAME, PASSWORD, EMAIL, COUNTRY);
    }
}
