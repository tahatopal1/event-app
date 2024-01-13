package com.project.userservice.mapper;

import com.project.userservice.dto.RoleDTO;
import com.project.userservice.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper implements ObjectMapper<Role, RoleDTO> {
    @Override
    public RoleDTO map(Role role) {
        return RoleDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .description(role.getDescription())
                .build();
    }

    @Override
    public Role mapDto(RoleDTO roleDTO) {
        return Role.builder()
                .id(roleDTO.getId())
                .name(roleDTO.getName())
                .description(roleDTO.getDescription())
                .build();
    }
}
