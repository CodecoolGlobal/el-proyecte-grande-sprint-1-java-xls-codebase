package com.codecool.oauth2_resource_server.controller;

import com.codecool.oauth2_resource_server.persistence.models.Article;
import com.codecool.oauth2_resource_server.persistence.repositories.ArticleRepository;
import com.codecool.oauth2_resource_server.persistence.repositories.SourceRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class NewsController {
    private ArticleRepository articleRepository;
    private SourceRepository sourceRepository;

    @PostMapping(value = "/api/news/articles")
    public ResponseEntity<Article> save(@RequestBody Article newArticle) {
        Article article = articleRepository.save(newArticle);
        return new ResponseEntity<>(article, HttpStatus.CREATED);
    }
}