package com.alvarolongueira.user.service.utils;

import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.rest.api.model.CreateUserRequest;

import lombok.experimental.UtilityClass;

import java.time.OffsetDateTime;

@UtilityClass
public class ModelFactory {
    public static final String FIRST_NAME = "FIRS_NAME_FIELD";
    public static final String LAST_NAME = "LAST_NAME_FIELD";
    public static final String LAST_NAME_PATCH = "LAST_NAME_PATCH_FIELD";
    public static final String LAST_NAME_PUT = "LAST_NAME_PUT_FIELD";
    public static final String NICKNAME = "NICKNAME_FIELD";
    public static final String PASSWORD = "PASSWORD_FIELD";
    public static final String EMAIL = "EMAIL_FIELD";
    public static final String COUNTRY = "COUNTRY_FIELD";
    public static final OffsetDateTime CREATED_AT =
            OffsetDateTime.parse("2025-05-08T09:40:07+02:00");
    public static final OffsetDateTime UPDATED_AT =
            OffsetDateTime.parse("2025-05-10T09:40:07+02:00");

    public static User getUser(String userId) {
        return new User(
                userId,
                FIRST_NAME,
                LAST_NAME,
                NICKNAME,
                PASSWORD,
                EMAIL,
                COUNTRY,
                CREATED_AT,
                UPDATED_AT);
    }

    public static CreateUserRequest getCreateUserRequest() {
        return new CreateUserRequest(FIRST_NAME, LAST_NAME, NICKNAME, PASSWORD, EMAIL, COUNTRY);
    }

    public static CreateUserRequest getCreateUserRequestForPatch() {
        return new CreateUserRequest(FIRST_NAME, LAST_NAME_PATCH, NICKNAME, PASSWORD, EMAIL, null);
    }

    public static CreateUserRequest getCreateUserRequestForPut() {
        return new CreateUserRequest(FIRST_NAME, LAST_NAME_PUT, NICKNAME, PASSWORD, EMAIL, null);
    }
}
