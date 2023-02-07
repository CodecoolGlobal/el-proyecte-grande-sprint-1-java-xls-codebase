package com.codecool.oauth2_authorization_server.users.service;

import com.codecool.oauth2_authorization_server.users.model.RoleType;
import com.codecool.oauth2_authorization_server.users.model.User;
import com.codecool.oauth2_authorization_server.users.model.UserRole;
import com.codecool.oauth2_authorization_server.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;

    public boolean exist(String username) {
        return userRepository.existsByUsername(username);
    }

    public User add(User user) {
        log.info("this is a test");
        UserRole userRole = UserRole.builder()
                .name(RoleType.USER)
                .build();

        Set<User> users = new HashSet<>(Set.of(user));
        userRole.setUsers(users);

        Set<UserRole> userRoles = new HashSet<>();

        if (userRoleService.exist(userRole.getName())) {
            UserRole existingUserRole = userRoleService.findByName(userRole.getName());
            existingUserRole.getUsers().add(user);
            userRoles.add(existingUserRole);
        } else {
            userRoles.add(userRole);
        }

        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setUserRoles(userRoles);

        return userRepository.save(user);
    }
}
