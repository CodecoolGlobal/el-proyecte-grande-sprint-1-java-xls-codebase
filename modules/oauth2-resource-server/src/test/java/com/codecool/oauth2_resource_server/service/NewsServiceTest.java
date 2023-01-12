package com.codecool.oauth2_resource_server.service;

import com.codecool.oauth2_resource_server.persistence.models.Article;
import com.codecool.oauth2_resource_server.persistence.models.Source;
import com.codecool.oauth2_resource_server.persistence.repositories.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NewsServiceTest {

    @InjectMocks
    private NewsService newsService;

    @Mock
    private ArticleRepository articleRepository;

    private Source source = Source.builder()
            .id("source")
            .name("Source")
            .build();

    private Article article = Article.builder()
            .source(source)
            .author("Author")
            .title("Title")
            .description("Description")
            .url("http://url.test")
            .urlToImage("http://urlToImage")
            .publishedAt("2023-01-11T01:19:56Z")
            .content("Content")
            .build();

    @Test
    void add_addNewArticle_isSuccess() {
        when(articleRepository.save(article)).thenReturn(article);

        Article addedArticle = newsService.add(article);
        assertThat(addedArticle).isNotNull();
    }

    @Test
    void findAll_findAllArticles_returnsListOfArticles() {
        List<Article> articles = List.of(article, article);
        when(articleRepository.findAll()).thenReturn(articles);

        List<Article> savedArticles = newsService.findAll();
        assertThat(savedArticles.size()).isEqualTo(articles.size());
    }
}