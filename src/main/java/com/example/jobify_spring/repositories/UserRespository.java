package com.example.jobify_spring.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.jobify_spring.models.User;

public interface UserRespository extends MongoRepository<User, ObjectId> {
  List<User> findAll();

  // Optional<User> findUserById(ObjectId _id);
}
