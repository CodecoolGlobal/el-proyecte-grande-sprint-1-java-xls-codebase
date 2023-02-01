package com.codecool.oauth2_resource_server.persistence.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "userjwt")
public class UserEntity {
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Article> articles;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    @NotNull
    private String username;
}
