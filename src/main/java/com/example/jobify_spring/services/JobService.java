package com.example.jobify_spring.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.jobify_spring.models.JobModel;
import com.example.jobify_spring.repositories.JobRepository;

@Service
public class JobService {
  @Autowired
  private JobRepository jobRepository;

  public JobModel getJobById(String id) {
    ObjectId objectId = new ObjectId(id);
    Optional<JobModel> jobOptional = jobRepository.findById(objectId);
    return jobOptional.orElse(null);
  }

  public List<JobModel> getAllJobs(String jobStatus, String jobType, String sort) {
    Sort sorted = sort != null && sort.equalsIgnoreCase("asc") ? Sort.by(Sort.Order.asc("createdAt"))
        : Sort.by(Sort.Order.desc("createdAt"));

    if (jobStatus != null && jobType != null && sort != null) {
      return jobRepository.findByJobStatusAndJobType(jobStatus, jobType, sorted);
    }

    if (jobStatus != null && jobType != null) {
      return jobRepository.findByJobStatusAndJobType(jobStatus, jobType);
    }

    if (jobStatus != null) {
      return jobRepository.findByJobStatus(jobStatus);
    }

    if (jobType != null) {
      return jobRepository.findByJobType(jobType);
    }

    return jobRepository.findAll();
  }

}
