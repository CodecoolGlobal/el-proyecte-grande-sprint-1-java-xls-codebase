package com.codecool.oauth2_authorization_server.users.service;

import com.codecool.oauth2_authorization_server.users.model.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OidcUserInfoService {

    CustomUserDetailsService customUserDetailsService;

    public OidcUserInfo loadUser(String username) {
        CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(username);

        return OidcUserInfo.builder()
                .subject(customUserDetails.getUsername())
                .givenName(customUserDetails.getFirstName())
                .build();
    }


}
