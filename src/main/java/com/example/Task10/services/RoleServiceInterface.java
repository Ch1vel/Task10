package com.example.Task10.services;

import com.example.Task10.models.Role;

import java.util.List;

public interface RoleServiceInterface {
    Role getUserRole();
    Role getAdminRole();
    List<Role> showAllRoles();
}
