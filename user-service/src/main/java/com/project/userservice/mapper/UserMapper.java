package com.project.userservice.mapper;

import com.project.userservice.dto.UserDTO;
import com.project.userservice.entity.Role;
import com.project.userservice.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper implements ObjectMapper<User, UserDTO> {

    private final RoleMapper roleMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO map(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .roles(user.getRoles()
                        .stream()
                        .map(roleMapper::map)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public User reverseMap(UserDTO userDTO) {
        return User.builder()
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .build();
    }
}
