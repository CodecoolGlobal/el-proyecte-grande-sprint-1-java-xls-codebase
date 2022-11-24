package com.codecool.snoopnews.services.newsapi.newsApiClient;

import com.codecool.snoopnews.services.newsapi.models.request.EverythingRequest;
import com.codecool.snoopnews.services.newsapi.models.request.SourcesRequest;
import com.codecool.snoopnews.services.newsapi.models.request.TopHeadlinesRequest;
import com.codecool.snoopnews.services.newsapi.models.response.ArticleResponse;
import com.codecool.snoopnews.services.newsapi.models.response.SourcesResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;


@FeignClient(value = "newsApiClient", url = "${newsApi.baseUrl}", configuration = newsApiConfig.class)
public interface NewsApiClient {

    @GetMapping(value = "${newsApi.path.sources}")
    SourcesResponse getSources(@SpringQueryMap SourcesRequest sourcesRequest);

    @GetMapping(value = "${newsApi.path.topHeadlines}")
    ArticleResponse getTopHeadlines(@SpringQueryMap TopHeadlinesRequest topHeadlinesRequest);

    @GetMapping(value = "${newsApi.path.everything}")
    ArticleResponse getEverything(@SpringQueryMap Map<String, String> params);
}