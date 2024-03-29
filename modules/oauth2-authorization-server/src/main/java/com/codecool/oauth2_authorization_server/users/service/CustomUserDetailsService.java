package com.codecool.oauth2_authorization_server.users.service;

//import com.codecool.oauth2_authorization_server.users.model.User;
//import com.codecool.oauth2_authorization_server.users.model.UserRole;

import com.codecool.oauth2_authorization_server.users.model.CustomUserDetails;
import com.codecool.oauth2_authorization_server.users.model.User;
import com.codecool.oauth2_authorization_server.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new CustomUserDetails(user);
    }
}
