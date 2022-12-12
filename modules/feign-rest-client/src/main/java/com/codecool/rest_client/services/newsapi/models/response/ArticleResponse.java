package com.codecool.rest_client.services.newsapi.models.response;

import com.codecool.rest_client.services.newsapi.models.Article;
import lombok.Data;

import java.util.List;

@Data
public class ArticleResponse {
    private String status;
    private int totalResults;
    private List<Article> articles;
}
