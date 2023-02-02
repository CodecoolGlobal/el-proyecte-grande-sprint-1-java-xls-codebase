package com.codecool.oauth2_resource_server.persistence.repositories;

import com.codecool.oauth2_resource_server.persistence.models.Article;
import com.codecool.oauth2_resource_server.persistence.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Boolean existsArticleByUrlAndUserEntity(String url, UserEntity userEntity);

    void deleteByIdAndUserEntity(long id, UserEntity userEntity);

    List<Article> findArticleByUserEntity(UserEntity userEntity);
}
