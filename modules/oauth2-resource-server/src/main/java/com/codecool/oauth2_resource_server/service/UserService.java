package com.codecool.oauth2_resource_server.service;

import com.codecool.oauth2_resource_server.persistence.models.UserEntity;
import com.codecool.oauth2_resource_server.persistence.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public UserEntity findByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findFirstByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Username does not exist");
        }
        return user.get();
    }

}
