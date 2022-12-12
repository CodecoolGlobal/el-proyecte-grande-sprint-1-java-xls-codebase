package com.codecool.rest_client.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Component
public class CORSCustomizer {
    public void corsCustomizer(HttpSecurity http) throws Exception {
        http.cors(c -> {
            CorsConfigurationSource source = s -> {
                CorsConfiguration corsConfiguration = new CorsConfiguration();
                corsConfiguration.setAllowCredentials(true);
                corsConfiguration.setAllowedOrigins(List.of("http://127.0.0.1:3000", "http://127.0.0.1:8080"));
                corsConfiguration.setAllowedHeaders(List.of("*"));
                corsConfiguration.setAllowedMethods(List.of("*"));
                return corsConfiguration;
            };
            c.configurationSource(source);
        });
    }
}
