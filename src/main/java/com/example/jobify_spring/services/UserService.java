package com.example.jobify_spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jobify_spring.models.User;
import com.example.jobify_spring.repositories.UserRespository;

@Service
public class UserService {
  @Autowired
  private UserRespository userRespository;

  public List<User> getAllUsers() {
    return userRespository.findAll();
  }

  // public Optional<User> getUserById(ObjectId _id) {
  // return userRespository.findById(_id);
  // }
}
