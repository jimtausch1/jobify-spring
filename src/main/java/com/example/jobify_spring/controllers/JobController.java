package com.example.jobify_spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobify_spring.models.JobModel;
import com.example.jobify_spring.services.JobService;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
  @Autowired
  private JobService jobService;

  @GetMapping
  public List<JobModel> getAllJobs(@RequestParam(value = "jobStatus", required = false) String jobStatus,
      @RequestParam(value = "jobType", required = false) String jobType,
      @RequestParam(value = "sort", required = false) String sort) {
    return jobService.getAllJobs(jobStatus, jobType, sort);
  }

  @GetMapping("/{id}")
  public JobModel getJobById(@PathVariable String id) {
    return jobService.getJobById(id);
  }

}
