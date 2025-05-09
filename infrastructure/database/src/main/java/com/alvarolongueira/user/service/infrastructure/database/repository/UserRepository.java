package com.alvarolongueira.user.service.infrastructure.database.repository;

import com.alvarolongueira.user.service.infrastructure.database.repository.entity.UserEntity;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
        extends JpaRepository<UserEntity, String>, PagingAndSortingRepository<UserEntity, String> {}
