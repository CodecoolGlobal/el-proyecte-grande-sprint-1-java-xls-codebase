package com.codecool.oauth2_resource_server.service;

import com.codecool.oauth2_resource_server.persistence.repositories.SourceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SourceServiceTest {

    @InjectMocks
    private SourceService sourceService;

    @Mock
    private SourceRepository sourceRepository;

    @Test
    void sourceExists_callsSourceRepositoryExistsSourceByIdOrNameMethod() {
        sourceService.sourceExists(anyString(), anyString());
        verify(sourceRepository).existsSourceByIdOrName(anyString(), anyString());
    }

    @Test
    void findFirst_findFirstByIdOrNameMethod() {
        sourceService.findFirst(anyString(), anyString());
        verify(sourceRepository).findFirstByIdOrName(anyString(), anyString());
    }
}