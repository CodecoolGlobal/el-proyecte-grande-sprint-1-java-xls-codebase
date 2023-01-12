package com.codecool.oauth2_resource_server.controller;

import com.codecool.oauth2_resource_server.persistence.models.Article;
import com.codecool.oauth2_resource_server.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class NewsController {
    private NewsService newsService;

    @PostMapping(value = "/api/news/articles")
    public ResponseEntity<Article> save(@RequestBody Article article) {
        Article savedArticle = newsService.add(article);
        return new ResponseEntity<>(savedArticle, HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/news/articles")
    public List<Article> findAll() {
        List<Article> articles = newsService.findAll();
        return articles;
    }
}