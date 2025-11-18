package com.example.jobify_spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jobify_spring.models.UserModel;
import com.example.jobify_spring.repositories.UserRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  public List<UserModel> getAllUsers() {
    return userRepository.findAll();
  }

  // public Optional<User> getUserById(ObjectId _id) {
  // return userRespository.findById(_id);
  // }
}
