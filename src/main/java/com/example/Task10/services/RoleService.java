package com.example.Task10.services;

import com.example.Task10.models.Role;
import com.example.Task10.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService implements RoleServiceInterface {
    private final RoleRepository roleRepository;
    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public Role getUserRole() {
        return roleRepository.findById(1).get();
    }
    @Transactional
    public Role getAdminRole() {
        return roleRepository.findById(2).get();
    }
    @Transactional(readOnly = true)
    public List<Role> showAllRoles() {
        return roleRepository.findAll();
    }
}
