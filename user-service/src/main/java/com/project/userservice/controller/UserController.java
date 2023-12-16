package com.project.userservice.controller;

import com.project.userservice.dto.UserDTO;
import com.project.userservice.facade.UserAdminFacade;
import com.project.userservice.facade.UserFacade;
import com.project.userservice.util.GeneralUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserFacade userFacade;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUser(){
        return userFacade.findUserByUsername();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDTO updateUser(@RequestBody UserDTO userDTO){
        return userFacade.updateUser(userDTO);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(){
        userFacade.deleteUser();
    }

}
