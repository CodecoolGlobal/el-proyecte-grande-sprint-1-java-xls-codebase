package com.codecool.oauth2_resource_server.controller;

import com.codecool.oauth2_resource_server.persistence.models.Article;
import com.codecool.oauth2_resource_server.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    @GetMapping(value = "/api/news/articles")
    public ResponseEntity<List<Article>> findAll() {
        List<Article> articles = newsService.findAll();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/news/articles/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        newsService.delete(id);
    }
}