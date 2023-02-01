package com.codecool.oauth2_resource_server.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class ResourceServerConfig {

    private CORSCustomizer corsCustomizer;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        corsCustomizer.corsCustomizer((http));
        http.headers().frameOptions().disable();
        http
                .csrf().ignoringAntMatchers("/h2-console/**")
                .and()
                .authorizeRequests(authorize -> authorize
                        .antMatchers("/h2-console/**")
                        .permitAll()
                );
        http
                .authorizeRequests(authorize -> authorize
                        .anyRequest()
                        .authenticated())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        return http.build();
    }
}
