package com.codecool.oauth2_resource_server.controller;

import com.codecool.oauth2_resource_server.persistence.models.Article;
import com.codecool.oauth2_resource_server.persistence.models.UserEntity;
import com.codecool.oauth2_resource_server.service.NewsService;
import com.codecool.oauth2_resource_server.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@AllArgsConstructor
public class NewsController {
    private NewsService newsService;
    private static Logger logger = LoggerFactory.getLogger(NewsController.class);
    private UserService userService;

    @PostMapping(value = "/api/news/articles")
    public ResponseEntity<Article> save(@RequestBody Article article, @AuthenticationPrincipal Jwt jwt) {
        UserEntity userEntity;
        try {
            userEntity = userService.findByUsername(jwt.getSubject());

        } catch (Exception e) {
            userEntity = UserEntity.builder()
                    .username(jwt.getSubject())
                    .build();
        }
        article.setUserEntity(userEntity);

        if (userEntity.getId() > 0 && newsService.checkArticleExists(article)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Article already exists.");
        }

        Article savedArticle = newsService.add(article);
        return new ResponseEntity<>(savedArticle, HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/news/articles")
    public ResponseEntity<List<Article>> findAll(@AuthenticationPrincipal Jwt jwt) {
        UserEntity userEntity = userService.findByUsername(jwt.getSubject());
        List<Article> articles = newsService.findAll(userEntity);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/news/articles/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt) {
        UserEntity userEntity = userService.findByUsername(jwt.getSubject());
        newsService.delete(id, userEntity);
    }
}