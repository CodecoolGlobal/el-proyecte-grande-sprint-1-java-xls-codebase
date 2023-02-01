package com.codecool.oauth2_resource_server.service;

import com.codecool.oauth2_resource_server.persistence.models.UserEntity;
import com.codecool.oauth2_resource_server.persistence.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void findByUsername_callsRepositoryFindFirstByUsername() {
        UserEntity mockedUser = mock(UserEntity.class);
        Mockito.when(userRepository.findFirstByUsername(anyString())).thenReturn(Optional.of(mockedUser));

        UserEntity savedUser = userService.findByUsername(anyString());
        verify(userRepository).findFirstByUsername(anyString());
    }

    @Test
    void findByUsername_existingUser_returnsUserEntityObject() {
        UserEntity mockedUser = mock(UserEntity.class);
        Mockito.when(userRepository.findFirstByUsername(anyString())).thenReturn(Optional.of(mockedUser));

        UserEntity savedUser = userService.findByUsername(anyString());
    }

    @Test
    void findByUsername_nonExistingUser_throwsUsernameNotFoundException() {
        Mockito.when(userRepository.findFirstByUsername(anyString())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.findByUsername(anyString()));
    }
}