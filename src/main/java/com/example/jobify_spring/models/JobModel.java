package com.example.jobify_spring.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "jobs")
public class JobModel {
  @Id
  private ObjectId id;
  private String company;
  private String position;
  private String jobStatus;
  private String jobType;
  private String jobLocation;
  private String createdAt;
}
