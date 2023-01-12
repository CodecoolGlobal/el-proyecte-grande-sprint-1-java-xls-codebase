package com.codecool.oauth2_resource_server.service;

import com.codecool.oauth2_resource_server.persistence.models.Article;
import com.codecool.oauth2_resource_server.persistence.repositories.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    private ArticleRepository articleRepository;

    public NewsService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article add(Article article) {
        return articleRepository.save(article);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }
}
