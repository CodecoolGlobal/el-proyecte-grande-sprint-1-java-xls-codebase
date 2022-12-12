package com.codecool.oauth2_authorization_server.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class IdTokenCustomizerConfig {

//    @Bean
//    public OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer(
//            OidcUserInfoService userInfoService) {
//        return (context) -> {
//            if (OidcParameterNames.ID_TOKEN.equals(context.getTokenType().getValue())) {
//                OidcUserInfo userInfo = userInfoService.loadUser(
//                        context.getPrincipal().getName());
//                context.getClaims().claims(claims ->
//                        claims.putAll(userInfo.getClaims()));
//            }
//        };
//    }

}