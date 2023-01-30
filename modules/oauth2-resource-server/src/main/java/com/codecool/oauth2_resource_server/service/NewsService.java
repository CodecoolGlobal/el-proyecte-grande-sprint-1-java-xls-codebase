package com.codecool.oauth2_resource_server.service;

import com.codecool.oauth2_resource_server.persistence.models.Article;
import com.codecool.oauth2_resource_server.persistence.models.Source;
import com.codecool.oauth2_resource_server.persistence.repositories.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class NewsService {

    private ArticleRepository articleRepository;
    private SourceService sourceService;

    public Article add(Article article) {
        Source source = article.getSource();
        if (sourceService.sourceExists(source.getId(), source.getName())) {
            Source existingSource = sourceService.findFirst(source.getId(), source.getName());
            article.setSource(existingSource);
        }
        return articleRepository.save(article);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public boolean checkArticleExists(Article article) {
        return articleRepository.existsArticleByUrl(article.getUrl());
    }

    @Transactional
    public void delete(Long id) {
        articleRepository.deleteById(id);
    }
}
