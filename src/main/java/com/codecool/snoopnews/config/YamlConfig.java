package com.codecool.snoopnews.config;

import org.springframework.beans.factory.annotation.Value;

public class YamlConfig {
    @Value("{$(newsApi.apiKey}")
    private String newsApiKey;
}
