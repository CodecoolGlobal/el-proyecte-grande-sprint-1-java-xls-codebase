package com.codecool.oauth2_authorization_server.users.service;

import com.codecool.oauth2_authorization_server.users.model.RoleType;
import com.codecool.oauth2_authorization_server.users.model.UserRole;
import com.codecool.oauth2_authorization_server.users.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
@AllArgsConstructor
public class UserRoleService {

    private final RoleRepository roleRepository;

    public boolean exist(RoleType roleType) {
        return roleRepository.existsByName(roleType);
    }

    public UserRole findByName(RoleType roleType) {
        return roleRepository.findFirstByName(roleType);
    }

    public UserRole add(UserRole userRole) throws EntityExistsException {
        return roleRepository.save(userRole);
    }

}
