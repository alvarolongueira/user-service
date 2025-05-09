package com.alvarolongueira.user.service.domain.entity;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.With;

import java.time.OffsetDateTime;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@With
public class User {

    private String id;
    private String firstName;
    private String lastName;
    @NotNull private String nickname;
    private String password;
    @NotNull private String email;
    private String country;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
