package com.codecool.snoopnews.persistence;

import com.codecool.snoopnews.persistence.models.Article;
import com.codecool.snoopnews.persistence.models.Source;
import com.codecool.snoopnews.persistence.repositories.ArticleRepository;
import com.codecool.snoopnews.persistence.repositories.SourceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {
    private ArticleRepository articleRepository;
    private SourceRepository sourceRepository;

    public NewsController(ArticleRepository articleService) {
        this.articleRepository = articleService;
    }

    @PostMapping("news/api")
    public ResponseEntity<Article> save(@RequestBody Article newArticle) {
        Article article = articleRepository.save(newArticle);
        return new ResponseEntity<>(article, HttpStatus.CREATED);
    }
}