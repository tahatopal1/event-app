package com.project.userservice.controller;

import com.project.userservice.dto.UserDTO;
import com.project.userservice.facade.admin.UserAdminFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SignUpController {

    private final UserAdminFacade userAdminFacade;

    public SignUpController(UserAdminFacade userAdminFacade) {
        this.userAdminFacade = userAdminFacade;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDTO signup(@RequestBody UserDTO userDTO){
         return userAdminFacade.saveUser(userDTO);
    }

}
