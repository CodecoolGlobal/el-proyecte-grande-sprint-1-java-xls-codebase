package com.codecool.snoopnews.services.newsapi.newsApiClient;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class newsApiConfig {
    @Value("${newsApi.apiKey}")
    private String apiKey;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("X-API-KEY", apiKey);
        };
    }
}
