package com.codecool.oauth2_authorization_server.users.repository;

import com.codecool.oauth2_authorization_server.users.model.RoleType;
import com.codecool.oauth2_authorization_server.users.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, Long> {
    Optional<RoleRepository> findByName(RoleType name);
}
