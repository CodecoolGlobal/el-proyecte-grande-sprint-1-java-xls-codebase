package com.codecool.snoopnews.services.newsapi.models.response;

import com.codecool.snoopnews.services.newsapi.models.Source;
import lombok.Data;

import java.util.List;

@Data
public class SourcesResponse {
    private String status;
    private List<Source> sources;
}
