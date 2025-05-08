package com.alvarolongueira.user.service.infrastructure.database.adapter.mapper;

import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.infrastructure.database.repository.entity.UserEntity;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface EntityMapper {

    User toUser(UserEntity entity);

    UserEntity toUserEntity(User entity);

    default Instant toInstant(OffsetDateTime time) {
        return time.toInstant();
    }

    default OffsetDateTime toOffsetDateTime(Instant time) {
        return time.atOffset(ZoneOffset.ofHours(0));
    }
}
