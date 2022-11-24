package com.codecool.snoopnews.persistence.models;


import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity(name="source")
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int persistenceId;
    private String id;
    private String name;
    private String description;
    private String url;
    private String category;
    private String language;
    private String country;
    @OneToMany(mappedBy = "source")
    private Set<Article> articles;
}