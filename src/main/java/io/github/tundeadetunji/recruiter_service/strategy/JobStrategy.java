package io.github.tundeadetunji.recruiter_service.strategy;

import io.github.tundeadetunji.recruiter_service.domain.model.Job;

import java.util.Optional;

public interface JobStrategy {

    Job saveJob(Job job);
    Optional<Job> findJobById(Long jobId);
    Job updateJob(Long jobId, Job job);

}
