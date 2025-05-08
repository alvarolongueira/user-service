package com.alvarolongueira.user.service.domain.entity;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.springframework.lang.Nullable;

import java.time.OffsetDateTime;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class User {

    @Nullable private String id;
    @Nullable private String firstName;
    @Nullable private String lastName;
    private String nickname;
    @Nullable private String password;
    private String email;
    @Nullable private String country;
    @Nullable private OffsetDateTime createdAt;
    @Nullable private OffsetDateTime updatedAt;

    public User withId(@NotNull String id) {
        return this.toBuilder().id(id).build();
    }
}
