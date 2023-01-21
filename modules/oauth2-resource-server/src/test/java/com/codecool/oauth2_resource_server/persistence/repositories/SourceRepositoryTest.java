package com.codecool.oauth2_resource_server.persistence.repositories;

import com.codecool.oauth2_resource_server.persistence.models.Source;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@DataJpaTest
class SourceRepositoryTest {

    @Autowired
    SourceRepository sourceRepository;

    private Source source = Source.builder()
            .id("source")
            .name("Source")
            .build();

    @Test
    void existsSourceByIdOrName_SourceExists_returnsTrue() {
        sourceRepository.save(source);
        Boolean sourceExist = sourceRepository.existsSourceByIdOrName(source.getId(), source.getName());
        assertTrue(sourceExist);
    }

    @Test
    void findFirstByIdOrName_SourceExists_returnsSource() {
        Source savedSource = sourceRepository.save(source);
        sourceRepository.save(source);
        Source firstSourceFound = sourceRepository.findFirstByIdOrName(source.getId(), source.getName());
        assertEquals(savedSource.getPersistenceId(), firstSourceFound.getPersistenceId());
    }
}