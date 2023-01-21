package com.codecool.oauth2_resource_server.persistence.models;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long persistenceId;
    private String id;
    private String name;
    private String description;
    private String url;
    private String category;
    private String language;
    private String country;
    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Article> articles;
}