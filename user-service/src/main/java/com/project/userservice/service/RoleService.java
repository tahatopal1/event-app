package com.project.userservice.service;

import com.project.userservice.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAllRoles();

    Role findRole(Long id);

    Role saveRole(Role role);

    void deleteRole(Long id);

}
