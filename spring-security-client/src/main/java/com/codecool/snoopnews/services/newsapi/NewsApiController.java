package com.codecool.snoopnews.services.newsapi;

import com.codecool.snoopnews.services.newsapi.models.request.EverythingRequest;
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
    public String test (@RegisteredOAuth2AuthorizedClient("api-client-authorization-code")OAuth2AuthorizedClient client) {
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
                                         @RequestParam(required = false) Integer pageSize,
                                         @RequestParam(required = false) Integer page) {
        EverythingRequest everythingRequest = EverythingRequest.builder()
                .q(q)
                .sources(sources)
                .domains(domains)
                .from(from)
                .to(to)
                .language(language)
                .sortBy(sortBy)
                .pageSize(pageSize)
                .page(page)
                .build();
        return newsApiClient.getEverything(everythingRequest);
    }
}

