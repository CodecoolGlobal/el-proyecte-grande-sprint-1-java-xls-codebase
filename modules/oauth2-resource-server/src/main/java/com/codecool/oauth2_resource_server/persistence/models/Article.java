package com.codecool.oauth2_resource_server.persistence.models;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "source_id")
    private Source source;
    private String author;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    @Column(columnDefinition = "TEXT")
    private String content;

}

