package com.codecool.oauth2_resource_server.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class ResourceServerConfig {

    private CORSCustomizer corsCustomizer;


    //    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .mvcMatcher("/test**")
//                .authorizeRequests()
//                .mvcMatchers("/test**")
//                .access("hasAuthority('SCOPE_api.read')")
//                .and()
//                .oauth2ResourceServer()
//                .jwt();
//        return http.build();
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        corsCustomizer.corsCustomizer((http));
        return http
                .oauth2ResourceServer(
                        j -> j.jwt().jwkSetUri("http://auth-server:9000/oauth2/jwks")
                ).authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .build();
    }
}
