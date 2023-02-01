package com.codecool.oauth2_resource_server.persistence.repositories;

import com.codecool.oauth2_resource_server.persistence.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findFirstByUsername(String username);
}
