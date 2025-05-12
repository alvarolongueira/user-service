package com.alvarolongueira.user.service.domain.mapper;

import org.mapstruct.Mapper;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface DateMapper {

    default String toString(OffsetDateTime time) {
        if (Objects.isNull(time)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssXXX");
        return time.format(formatter);
    }

    default Instant toInstant(OffsetDateTime time) {
        if (Objects.isNull(time)) {
            return null;
        }
        return time.toInstant();
    }

    default OffsetDateTime toOffsetDateTime(Instant time) {
        if (Objects.isNull(time)) {
            return null;
        }
        return time.atOffset(ZoneOffset.ofHours(0));
    }
}
