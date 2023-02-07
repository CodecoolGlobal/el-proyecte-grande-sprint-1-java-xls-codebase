package com.codecool.oauth2_authorization_server.config;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "config.cors")
@Setter
public class CORSCustomizer {

    private List<String> allowedOrigins;
    private List<String> allowedHeaders;
    private List<String> allowedMethods;

    public void corsCustomizer(HttpSecurity http) throws Exception {
        http.cors(c -> {
            CorsConfigurationSource source = s -> {
                CorsConfiguration corsConfiguration = new CorsConfiguration();
                corsConfiguration.setAllowCredentials(true);
                corsConfiguration.setAllowedOriginPatterns(allowedOrigins);
                corsConfiguration.setAllowedHeaders(allowedHeaders);
                corsConfiguration.setAllowedMethods(allowedMethods);
                return corsConfiguration;
            };
            c.configurationSource(source);
        });
    }
}
