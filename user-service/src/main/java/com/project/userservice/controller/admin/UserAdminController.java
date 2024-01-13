package com.project.userservice.controller.admin;

import com.project.userservice.dto.UserDTO;
import com.project.userservice.facade.admin.UserAdminFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/users")
public class UserAdminController {

    private final UserAdminFacade userAdminFacade;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUsers(){
        return userAdminFacade.findAllUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUser(@PathVariable("id") Long id){
        return userAdminFacade.findUser(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO saveUser(@RequestBody UserDTO userDTO){
        return userAdminFacade.saveUser(userDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDTO updateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTO){
        return userAdminFacade.updateUser(id, userDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long id){
        userAdminFacade.deleteUser(id);
    }

    @PostMapping("/{id}/role/{roleId}")
    @ResponseStatus(HttpStatus.OK)
    public void assignRole(@PathVariable("id") Long id, @PathVariable("roleId") Long roleId){
        userAdminFacade.assignRole(id, roleId);
    }

    @DeleteMapping("/{id}/role/{roleId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeRole(@PathVariable("id") Long id, @PathVariable("roleId") Long roleId){
        userAdminFacade.removeRole(id, roleId);
    }

}
