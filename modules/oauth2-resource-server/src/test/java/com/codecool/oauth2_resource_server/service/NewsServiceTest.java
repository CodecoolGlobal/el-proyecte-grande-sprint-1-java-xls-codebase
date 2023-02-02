package com.codecool.oauth2_resource_server.service;

import com.codecool.oauth2_resource_server.persistence.models.Article;
import com.codecool.oauth2_resource_server.persistence.models.Source;
import com.codecool.oauth2_resource_server.persistence.models.UserEntity;
import com.codecool.oauth2_resource_server.persistence.repositories.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class NewsServiceTest {

    @InjectMocks
    private NewsService newsService;

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private SourceService sourceService;

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
    void add_Article_callsRepositorySaveMethod() {
        newsService.add(article);
        verify(articleRepository).save(article);
    }

    @Test
    void add_Article_callsSourceServiceSourceExists() {
        newsService.add(article);
        verify(sourceService).sourceExists(anyString(), anyString());
    }

    @Test
    void add_ArticleWithExistingSource_callsSourceServiceFindFirstMethod() {
        Mockito.when(sourceService.sourceExists(anyString(), anyString()))
                .thenReturn(true);

        newsService.add(article);
        verify(sourceService).findFirst(anyString(), anyString());
    }

    @Test
    void add_ArticleWithExistingSource_replaceArticleSourceWithExistingSource() {
        Mockito.when(sourceService.sourceExists(anyString(), anyString()))
                .thenReturn(true);
        Mockito.when(sourceService.findFirst(anyString(), anyString()))
                .thenReturn(Source.builder().persistenceId(1).build());

        newsService.add(article);
        assertTrue(article.getSource().getPersistenceId() > 0);

    }

    @Test
    void checkArticleExists_shouldCallExistsArticleByUrlAndUserEntity() {
        UserEntity mockedUser = mock(UserEntity.class);
        article.setUserEntity(mockedUser);
        newsService.checkArticleExists(article);
        verify(articleRepository).existsArticleByUrlAndUserEntity(article.getUrl(), mockedUser);
    }

    @Test
    void delete_should_callDeleteArticleByIdAndUserEntity() {
        UserEntity mockedUser = mock(UserEntity.class);
        long id = 0L;
        newsService.delete(id, mockedUser);
        verify(articleRepository).deleteByIdAndUserEntity(id, mockedUser);
    }
}