package com.alvarolongueira.user.service.infrastructure.database.repository;

import com.alvarolongueira.user.service.infrastructure.database.repository.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {}
