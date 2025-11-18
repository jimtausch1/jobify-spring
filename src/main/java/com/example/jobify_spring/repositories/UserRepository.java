package com.example.jobify_spring.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNull;

import com.example.jobify_spring.models.UserModel;

public interface UserRepository extends MongoRepository<UserModel, ObjectId> {
  @NonNull
  List<UserModel> findAll();

  // Optional<User> findUserById(ObjectId _id);
}
