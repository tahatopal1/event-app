package com.project.userservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RoleDTO {

    private Long id;
    private String name;
    private String code;
    private String description;

}