package com.codecool.oauth2_resource_server.persistence.repositories;

import com.codecool.oauth2_resource_server.persistence.models.Article;
import com.codecool.oauth2_resource_server.persistence.models.Source;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;


@DataJpaTest
class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;

    Source source = Source.builder()
            .id("source")
            .name("Source")
            .build();

    Article article = Article.builder()
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
    public void save_saveArticle_returnsSavedArticleWithIdGreaterThenZero() {
        Article savedArticle = articleRepository.save(article);
        assertThat(savedArticle).isNotNull();
        assertThat(savedArticle.getId()).isGreaterThan(0);
    }

    @Test
    public void deleteArticleByID_deleteArticleWithExistingId_thenDeletingShouldBeSuccessful() {
        Article savedArticle = articleRepository.save(article);
        Long articleId = savedArticle.getId();
        articleRepository.deleteById(articleId);
        boolean deletedArticle = articleRepository.existsById(articleId);
        assertFalse(deletedArticle);
    }
}