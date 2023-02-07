package com.codecool.oauth2_authorization_server.users.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Enumerated(EnumType.STRING)
    private RoleType name;
    @ManyToMany(mappedBy = "userRoles", cascade = CascadeType.ALL)
    @JsonIgnore
    Set<User> users;
}

