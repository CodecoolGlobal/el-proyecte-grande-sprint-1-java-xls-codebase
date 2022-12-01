package com.codecool.oauth2_authorization_server.config;

import com.codecool.oauth2_authorization_server.users.model.RoleType;
import com.codecool.oauth2_authorization_server.users.model.User;
import com.codecool.oauth2_authorization_server.users.model.UserRole;
import com.codecool.oauth2_authorization_server.users.repository.RoleRepository;
import com.codecool.oauth2_authorization_server.users.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class CreateUser {
    UserRepository userRepository;
    RoleRepository roleRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public CreateUser(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        init();
    }

    public void init() {
        String encodedPassword = bCryptPasswordEncoder.encode("password");
        UserRole userRole = UserRole.builder()
                .name(RoleType.USER)
                .build();

        User user = User.builder()
                .username("user")
                .email("email@email.com")
                .password(encodedPassword)
                .firstName("Axel")
                .lastName("K.")
                .userRoles(Set.of(userRole))
                .build();

        userRepository.save(user);
    }
}
