package com.codecool.oauth2_authorization_server.controller;

import com.codecool.oauth2_authorization_server.users.model.User;
import com.codecool.oauth2_authorization_server.users.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> register(@Valid @RequestBody User user) {
        if (userService.exist(user.getUsername())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Username already exists.");
        }
        userService.add(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
