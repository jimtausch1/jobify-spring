package com.example.jobify_spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobify_spring.models.UserModel;
import com.example.jobify_spring.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping
  public List<UserModel> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  public UserModel getUserById(@PathVariable String id) {
    return userService.getUserById(id);
  }

}
