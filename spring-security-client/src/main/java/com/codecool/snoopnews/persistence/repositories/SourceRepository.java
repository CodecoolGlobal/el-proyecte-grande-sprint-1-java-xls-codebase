package com.codecool.snoopnews.persistence.repositories;

import com.codecool.snoopnews.persistence.models.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends JpaRepository<Source, Long> {
}
