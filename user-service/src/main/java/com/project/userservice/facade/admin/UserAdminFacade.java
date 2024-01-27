package com.project.userservice.facade.admin;

import com.project.userservice.dto.UserDTO;
import com.project.userservice.entity.Role;
import com.project.userservice.entity.User;
import com.project.userservice.mapper.UserMapper;
import com.project.userservice.service.RoleService;
import com.project.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserAdminFacade {

    private final UserService userService;
    private final RoleService roleService;
    private final UserMapper  userMapper;

    public List<UserDTO> findAllUsers(){
        return userService.findAllUsers()
                .stream()
                .map(userMapper::map)
                .toList();
    }

    public UserDTO findUser(Long id){
        User user = userService.findUser(id);
        return userMapper.map(user);
    }

    public UserDTO saveUser(UserDTO userDTO){
        User user = userMapper.mapDto(userDTO);
        return saveAndMap(user);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        userService.findUser(id); // control if it exists
        User savingUser = userMapper.mapDto(userDTO);
        savingUser.setId(id);
        return saveAndMap(savingUser);
    }

    public void deleteUser(Long id){
        userService.deleteUser(id);
    }

    private UserDTO saveAndMap(User user) {
        User savedUser = userService.saveUser(user);
        return userMapper.map(savedUser);
    }

    public void assignRole(Long userId, Long roleId){
        User user = userService.findUser(userId);
        Role role = roleService.findRole(roleId);
        user.getRoles().add(role);
        userService.saveUser(user);
    }

    public void removeRole(Long userId, Long roleId){
        User user = userService.findUser(userId);
        Role role = roleService.findRole(roleId);
        user.getRoles().remove(role);
        userService.saveUser(user);
    }

}
