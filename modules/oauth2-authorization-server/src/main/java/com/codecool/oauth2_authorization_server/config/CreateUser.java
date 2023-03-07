package com.codecool.oauth2_authorization_server.config;

import com.codecool.oauth2_authorization_server.users.model.User;
import com.codecool.oauth2_authorization_server.users.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@Configuration
@AllArgsConstructor
public class CreateUser {

    private final UserService userService;
    PasswordEncoder passwordEncoder;


    @PostConstruct
    public void init() {
//        String encodedPassword = passwordEncoder.encode("password");

        User user = User.builder()
                .username("user")
                .email("email@email.com")
//                .password(encodedPassword)
                .password("password")
                .firstName("Axel")
                .lastName("K.")
                .build();

        userService.add(user);
    }
}
