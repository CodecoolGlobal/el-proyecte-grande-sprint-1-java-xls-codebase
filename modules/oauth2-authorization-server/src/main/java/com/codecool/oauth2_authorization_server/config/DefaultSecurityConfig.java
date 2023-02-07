package com.codecool.oauth2_authorization_server.config;

import com.codecool.oauth2_authorization_server.users.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class DefaultSecurityConfig {

    private CORSCustomizer corsCustomizer;
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authProvider())
                .build();
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
        corsCustomizer.corsCustomizer((http));
        http.headers().frameOptions().disable();
        http
                .csrf().ignoringAntMatchers("/h2-console/**")
                .and()
                .authorizeRequests(authorize -> authorize
                        .antMatchers("/h2-console/**")
                        .permitAll()
                );
        http.csrf(csrf -> csrf
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        );
        http

                .authorizeRequests(authorize -> authorize
                        .antMatchers("/user")
                        .permitAll()
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
