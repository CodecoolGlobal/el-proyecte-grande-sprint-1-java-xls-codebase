package com.codecool.config.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestCustomizers;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.oauth2.client.web.server.DefaultServerOAuth2AuthorizationRequestResolver;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationEntryPoint;

//@EnableWebSecurity
//public class WebSecurityConfig {
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http, ClientRegistrationRepository clientRegistrationRepository) throws Exception {
//        String base_uri = OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI;
//        DefaultOAuth2AuthorizationRequestResolver resolver = new DefaultOAuth2AuthorizationRequestResolver(clientRegistrationRepository, base_uri);
//        resolver.setAuthorizationRequestCustomizer(OAuth2AuthorizationRequestCustomizers.withPkce());
//        http
////                .authorizeHttpRequests(authorize -> authorize
////                        .antMatchers("/login").authenticated())
//                .oauth2Login(oauth2Login -> oauth2Login
//                        .loginPage(
//                                OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI + "/gateway-client-oidc")
//                        .authorizationEndpoint().authorizationRequestResolver(resolver)
//                )
//                .oauth2Client(Customizer.withDefaults());
//        return http.build();
//    }

@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfig {

    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, ReactiveClientRegistrationRepository clientRegistrationRepository) throws Exception {
        String base_uri = OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI;
//            ServerWebExchangeMatcher authorizationRequestMatcher = new PathPatternParserServerWebExchangeMatcher(
//                    base_uri + "/gateway-client-oidc?requestmatcher=true"
//            );
//            DefaultServerOAuth2AuthorizationRequestResolver resolver = new DefaultServerOAuth2AuthorizationRequestResolver(
//                    clientRegistrationRepository, authorizationRequestMatcher);
        DefaultServerOAuth2AuthorizationRequestResolver resolver = new DefaultServerOAuth2AuthorizationRequestResolver(
                clientRegistrationRepository);
        resolver.setAuthorizationRequestCustomizer(
                OAuth2AuthorizationRequestCustomizers.withPkce());
        http
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(new RedirectServerAuthenticationEntryPoint(
                                base_uri + "/gateway-client-oidc")))
                .oauth2Login(oauth2 -> oauth2
                        .authorizationRequestResolver(resolver)

                )
                .authorizeExchange(authorize -> authorize
                        .pathMatchers("/login").authenticated()
                        .anyExchange().permitAll()
                );
//                    .oauth2Login(oauth2Login -> oauth2Login
//                            .loginPage(
//                                    OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI + "/gateway-client-oidc")
//                            .authorizationEndpoint().authorizationRequestResolver(resolver)
//                    )
//                    .oauth2Client(Customizer.withDefaults());
        return http.build();
    }

}
