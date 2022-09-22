package com.codecool.snoopnews.services.newsapi.models;


import lombok.Data;


@Data
public class Source {
    private String id;
    private String name;
    private String description;
    private String url;
    private String category;
    private String language;
    private String country;
}
