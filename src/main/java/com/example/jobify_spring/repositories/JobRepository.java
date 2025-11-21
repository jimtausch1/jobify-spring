package com.example.jobify_spring.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNull;

import com.example.jobify_spring.models.JobModel;

public interface JobRepository extends MongoRepository<JobModel, ObjectId> {
  @NonNull
  List<JobModel> findAll();

  @NonNull
  List<JobModel> findByJobType(String jobType);

  @NonNull
  List<JobModel> findByJobStatus(String jobStatus);

  @NonNull
  List<JobModel> findByJobStatusAndJobType(String jobStatus, String jobType);

  @NonNull
  List<JobModel> findByJobStatusAndJobType(String jobStatus, String jobType, Sort sort);
}
