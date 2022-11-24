package com.codecool.snoopnews.services.newsapi.models.request;

import lombok.Builder;

@Builder
public class TopHeadlinesRequest {
    private String category;
    private String sources;
    private String q;
    private Integer pageSize;
    private Integer page;
    private String country;
    private String language;
}
