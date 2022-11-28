package com.codecool.snoopnews.persistence.repositories;

import com.codecool.snoopnews.persistence.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}