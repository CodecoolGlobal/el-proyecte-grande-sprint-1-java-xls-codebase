package com.codecool.oauth2_resource_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class ResourceServerConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .mvcMatcher("/test**")
                .authorizeRequests()
                .mvcMatchers("/test**")
                .access("hasAuthority('SCOPE_api.read')")
                .and()
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }
}
