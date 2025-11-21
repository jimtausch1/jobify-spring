package com.example.jobify_spring.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jobify_spring.models.UserModel;
import com.example.jobify_spring.repositories.UserRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  public UserModel getUserById(String id) {
    ObjectId objectId = new ObjectId(id);
    Optional<UserModel> userOptional = userRepository.findById(objectId);
    return userOptional.orElse(null);
  }

  public List<UserModel> getAllUsers() {
    return userRepository.findAll();
  }
}
