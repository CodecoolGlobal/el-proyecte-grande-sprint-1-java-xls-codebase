package com.codecool.rest_client.services.newsapi.models.request;

import lombok.Builder;

@Builder
public class SourcesRequest {
    private String category, language, country;
}
