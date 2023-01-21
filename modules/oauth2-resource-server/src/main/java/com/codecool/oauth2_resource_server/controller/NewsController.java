package com.codecool.oauth2_resource_server.controller;

import com.codecool.oauth2_resource_server.persistence.models.Article;
import com.codecool.oauth2_resource_server.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@AllArgsConstructor
public class NewsController {
    private NewsService newsService;

    @PostMapping(value = "/api/news/articles")
    public ResponseEntity<Article> save(@RequestBody Article article) {
        if (newsService.checkArticleExists(article)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Article already exists.");
        }
        Article savedArticle = newsService.add(article);
        return new ResponseEntity<>(savedArticle, HttpStatus.CREATED);
    }
}