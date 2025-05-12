package com.alvarolongueira.user.service.infrastructure.database.adapter.mapper;

import com.alvarolongueira.user.service.domain.entity.PageableUser;
import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.domain.mapper.DateMapper;
import com.alvarolongueira.user.service.infrastructure.database.repository.entity.UserEntity;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

@Mapper(
        componentModel = "spring",
        uses = {DateMapper.class})
public interface EntityMapper {

    User toUser(UserEntity entity);

    UserEntity toUserEntity(User entity);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    UserEntity toUserEntityUpdate(User user, @MappingTarget UserEntity entity);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    UserEntity toUserEntityPatch(User user, @MappingTarget UserEntity entity);

    @Mapping(source = "content", target = "userList")
    @Mapping(source = "number", target = "pageNumber")
    PageableUser toPage(Page<UserEntity> page);
}
