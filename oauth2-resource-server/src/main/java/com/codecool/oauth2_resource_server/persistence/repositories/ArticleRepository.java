package com.codecool.oauth2_resource_server.persistence.repositories;

import com.codecool.oauth2_resource_server.persistence.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
