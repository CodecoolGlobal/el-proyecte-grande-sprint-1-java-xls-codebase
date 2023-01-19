package com.codecool.oauth2_resource_server.controller;

import com.codecool.oauth2_resource_server.persistence.models.Article;
import com.codecool.oauth2_resource_server.persistence.models.Source;
import com.codecool.oauth2_resource_server.service.NewsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NewsController.class)
class NewsControllerTest {

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
    @MockBean
    private NewsService newsService;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mvc;

    @Test
    void save_validJwtAndArticle_returnsStatusCreated() throws Exception {
        mvc.perform(post("/api/news/articles")
                        .with(SecurityMockMvcRequestPostProcessors.jwt())
                        .content(asJsonString(article))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void save_validJwtAndNullValue_returnsStatus400() throws Exception {
        mvc.perform(post("/api/news/articles")
                        .with(SecurityMockMvcRequestPostProcessors.jwt())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void save_noJwtAndNullValue_returnsStatus403() throws Exception {
        mvc.perform(post("/api/news/articles")
                        .content(asJsonString(article))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}