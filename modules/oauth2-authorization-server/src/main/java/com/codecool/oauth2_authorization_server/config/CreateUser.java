package com.codecool.oauth2_authorization_server.config;

import com.codecool.oauth2_authorization_server.users.model.RoleType;
import com.codecool.oauth2_authorization_server.users.model.User;
import com.codecool.oauth2_authorization_server.users.model.UserRole;
import com.codecool.oauth2_authorization_server.users.service.UserRoleService;
import com.codecool.oauth2_authorization_server.users.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@AllArgsConstructor
public class CreateUser {

    private final UserService userService;
    private final UserRoleService userRoleService;
    PasswordEncoder passwordEncoder;


    public void init() {
        String encodedPassword = passwordEncoder.encode("password");

        UserRole userRole = UserRole.builder()
                .name(RoleType.USER)
                .build();

        UserRole savedUserRole = userRoleService.add(userRole);


        User user = User.builder()
                .username("user")
                .email("email@email.com")
                .password(encodedPassword)
                .firstName("Axel")
                .lastName("K.")
                .userRoles(Set.of(savedUserRole))
                .build();

        userService.add(user);
    }
}
