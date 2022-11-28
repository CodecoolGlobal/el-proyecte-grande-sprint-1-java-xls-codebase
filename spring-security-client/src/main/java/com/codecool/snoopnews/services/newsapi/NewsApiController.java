package com.codecool.snoopnews.services.newsapi;

import com.codecool.snoopnews.services.newsapi.models.response.ArticleResponse;
import com.codecool.snoopnews.services.newsapi.newsApiClient.NewsApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;


@RestController
@RequiredArgsConstructor
@CrossOrigin
public class NewsApiController {

    private final NewsApiClient newsApiClient;
    private final WebClient webClient;


    //    @GetMapping("top-headlines/sources")
//    public SourcesResponse getSources(@RequestParam(required = false) String language,
//                                      @RequestParam(required = false) String category,
//                                      @RequestParam(required = false) String country) {
//        SourcesRequest sourcesRequest = SourcesRequest.builder()
//                .language(language)
//                .category(category)
//                .country(country)
//                .build();
//        return newsApiClient.getSources(sourcesRequest);
//    }
//
//    @GetMapping("top-headlines")
//    public ArticleResponse getTopHeadlines(@RequestParam(required = false) String category,
//                                           @RequestParam(required = false) String sources,
//                                           @RequestParam(required = false) String q,
//                                           @RequestParam(required = false) Integer pageSize,
//                                           @RequestParam(required = false) Integer page,
//                                           @RequestParam(required = false) String country,
//                                           @RequestParam(required = false) String language) {
//        TopHeadlinesRequest topHeadlinesRequest = TopHeadlinesRequest.builder()
//                .category(category)
//                .sources(sources)
//                .q(q)
//                .pageSize(pageSize)
//                .page(page)
//                .country(country)
//                .language(language)
//                .build();
//        return newsApiClient.getTopHeadlines(topHeadlinesRequest);
//    }

    @GetMapping("/test/resource")
    public String test(@RegisteredOAuth2AuthorizedClient("api-client-authorization-code") OAuth2AuthorizedClient client) {
        return this.webClient
                .get()
                .uri("http://127.0.0.1:8090/test/resource")
                .attributes(ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(client))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @CrossOrigin
    @GetMapping("/everything")
    public ArticleResponse getEverything(@RequestParam(required = false) String q,
                                         @RequestParam(required = false) String sources,
                                         @RequestParam(required = false) String domains,
                                         @RequestParam(required = false) String from,
                                         @RequestParam(required = false) String to,
                                         @RequestParam(required = false) String language,
                                         @RequestParam(required = false) String sortBy,
                                         @RequestParam(required = false) String pageSize,
                                         @RequestParam(required = false) String page) {

        Map<String, String> everythingRequestParams = new LinkedHashMap<>();
        everythingRequestParams.put("q", q);
        everythingRequestParams.put("sources", sources);
        everythingRequestParams.put("domains", domains);
        everythingRequestParams.put("from", from);
        everythingRequestParams.put("to", to);
        everythingRequestParams.put("language", language);
        everythingRequestParams.put("sortBy", sortBy);
        everythingRequestParams.put("pageSize", pageSize);
        everythingRequestParams.put("page", page);

        everythingRequestParams.values().removeIf(Objects::isNull);
        return newsApiClient.getEverything(everythingRequestParams);
    }
}

