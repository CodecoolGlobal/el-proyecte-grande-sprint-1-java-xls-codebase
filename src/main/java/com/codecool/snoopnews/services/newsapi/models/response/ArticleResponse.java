package com.codecool.snoopnews.services.newsapi.models.response;

import com.codecool.snoopnews.services.newsapi.models.Article;
import lombok.Data;

import java.util.List;

@Data
public class ArticleResponse {
    private String status;
    private int totalResults;
    private List<Article> articles;
}
