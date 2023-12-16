package com.project.authservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LoginController {

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void login(HttpServletRequest request){
        log.info("Successfully logged in: {}", request.getUserPrincipal().getName());
    }

}
