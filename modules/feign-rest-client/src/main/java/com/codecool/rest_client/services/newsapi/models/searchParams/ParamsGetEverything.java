package com.codecool.rest_client.services.newsapi.models.searchParams;

import lombok.Builder;

@Builder
public class ParamsGetEverything {
    String q, sources, domains, from, to, language, sortBy, pageSize, page;
}
