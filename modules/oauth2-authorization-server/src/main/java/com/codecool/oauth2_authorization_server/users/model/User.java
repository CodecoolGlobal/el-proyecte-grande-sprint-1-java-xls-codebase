package com.codecool.oauth2_authorization_server.users.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "oauth_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    @NotBlank
    private String username;
    @Column(unique = true)
    @Email
    private String email;
    @NotBlank
    private String password;
    private String firstName;
    private String lastName;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<UserRole> userRoles = new HashSet<>();
}

