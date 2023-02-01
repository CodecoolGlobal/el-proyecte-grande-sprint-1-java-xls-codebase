package com.codecool.oauth2_resource_server.persistence.repositories;

import com.codecool.oauth2_resource_server.persistence.models.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    UserEntity user = UserEntity.builder()
            .username("test")
            .build();

    @Test
    void findFirstByUsername_calls_returnsExistingUser() {
        userRepository.save(user);
        Optional<UserEntity> savedUser = userRepository.findFirstByUsername(user.getUsername());
        assertTrue(savedUser.isPresent());
    }

    @Test
    void findFirstByUsername_findExistingUser_returnsExistingUser() {
        userRepository.save(user);
        Optional<UserEntity> savedUser = userRepository.findFirstByUsername(user.getUsername());
        assertTrue(savedUser.isPresent());
    }
}