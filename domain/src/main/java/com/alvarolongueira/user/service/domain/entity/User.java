package com.alvarolongueira.user.service.domain.entity;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.springframework.lang.Nullable;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class User {

    @Nullable String id;
    @Nullable String firstName;
    @Nullable String lastName;
    String nickname;
    @Nullable String password;
    String email;
    @Nullable String country;

    public User withId(@NotNull String id) {
        return this.toBuilder().id(id).build();
    }
}
