package com.codecool.rest_client.services.newsapi.models.request;

import lombok.Builder;

@Builder
public class EverythingRequest {
    private String q;
    private String sources;
    private String domains;
    private String from;
    private String to;
    private String language;
    private String sortBy;
    private Integer pageSize;
    private Integer page;
}
