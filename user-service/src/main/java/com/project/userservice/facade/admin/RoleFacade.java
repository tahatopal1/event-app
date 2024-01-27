package com.project.userservice.facade.admin;

import com.project.userservice.dto.RoleDTO;
import com.project.userservice.entity.Role;
import com.project.userservice.mapper.RoleMapper;
import com.project.userservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleFacade {

    private final RoleService roleService;
    private final RoleMapper roleMapper;

    public List<RoleDTO> findAllRoles(){
        return roleService.findAllRoles()
                .stream()
                .map(roleMapper::map)
                .toList();
    }

    public RoleDTO findRole(Long id){
        Role role = roleService.findRole(id);
        return roleMapper.map(role);
    }

    public RoleDTO saveRole(RoleDTO roleDTO){
        Role role = roleMapper.mapDto(roleDTO);
        return saveAndMap(role);
    }

    public RoleDTO updateRole(Long id, RoleDTO roleDTO){
        findRole(id); // control if role exists
        Role savingRole = roleMapper.mapDto(roleDTO);
        savingRole.setId(id);
        return saveAndMap(savingRole);
    }

    public void deleteRole(Long id){
        roleService.deleteRole(id);
    }

    private RoleDTO saveAndMap(Role role) {
        Role savedRole = roleService.saveRole(role);
        return roleMapper.map(savedRole);
    }

}
