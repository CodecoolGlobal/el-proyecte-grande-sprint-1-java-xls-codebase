package com.codecool.snoopnews;

import com.codecool.snoopnews.services.newsapilib.NewsApiClient;
import com.codecool.snoopnews.services.newsapilib.models.Article;
import com.codecool.snoopnews.services.newsapilib.models.request.EverythingRequest;
import com.codecool.snoopnews.services.newsapilib.models.response.ArticleResponse;
import com.codecool.snoopnews.services.newsapilib.network.APIClient;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(path = "/news")
public class NewsController {

    @GetMapping()
    public void test (@RequestParam String query) {

        NewsApiClient newsApiClient = new NewsApiClient("0b5c38466d0a450bbb714c52faa8f5bc", new APIClient());
// /v2/everything
        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q(query)
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        System.out.println(response.getTotalResults());
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                }
        );

    }
}
