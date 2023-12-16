package com.project.userservice.service;

import com.project.userservice.entity.User;

import java.util.List;


public interface UserService {

    List<User> findAllUsers();

    User findUser(Long id);

    User saveUser(User user);

    void deleteUser(Long id);


}
