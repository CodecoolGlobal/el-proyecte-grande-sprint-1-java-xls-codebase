package com.codecool.oauth2_resource_server.service;

import com.codecool.oauth2_resource_server.persistence.models.Source;
import com.codecool.oauth2_resource_server.persistence.repositories.SourceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SourceService {

    private SourceRepository sourceRepository;

    public boolean sourceExists(String id, String name) {
        return sourceRepository.existsSourceByIdOrName(id, name);
    }

    public Source findFirst(String id, String name) {
        return sourceRepository.findFirstByIdOrName(id, name);

    }

}
