package com.alvarolongueira.user.service.infrastructure.database.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import java.time.Instant;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_table")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Nullable private String firstName;
    @Nullable private String lastName;
    private String nickname;
    @Nullable private String password;
    private String email;
    @Nullable private String country;
    @CreationTimestamp private Instant createdAt;
    @UpdateTimestamp @Nullable private Instant updatedAt;
    @Version private Long version;
}
