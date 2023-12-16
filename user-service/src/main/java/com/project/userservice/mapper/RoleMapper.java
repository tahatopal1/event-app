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
                .code(role.getCode())
                .description(role.getDescription())
                .build();
    }

    @Override
    public Role reverseMap(RoleDTO roleDTO) {
        return Role.builder()
                .id(roleDTO.getId())
                .name(roleDTO.getName())
                .code(roleDTO.getCode())
                .description(roleDTO.getDescription())
                .build();
    }
}
