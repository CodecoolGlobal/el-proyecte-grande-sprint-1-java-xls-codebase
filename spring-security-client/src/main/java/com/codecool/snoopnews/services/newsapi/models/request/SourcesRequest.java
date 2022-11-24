package com.codecool.snoopnews.services.newsapi.models.request;

import lombok.Builder;

@Builder
public class SourcesRequest {
    private String category, language, country;
}
