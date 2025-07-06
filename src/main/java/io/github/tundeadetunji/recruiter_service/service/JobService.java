package io.github.tundeadetunji.recruiter_service.service;

import io.github.tundeadetunji.recruiter_service.domain.model.Job;
import io.github.tundeadetunji.recruiter_service.dto.CreateJobDto;
import io.github.tundeadetunji.recruiter_service.dto.UpdateJobDto;

public interface JobService {
    Job createJob(CreateJobDto dto);
    Job updateJob(Long jobId, UpdateJobDto dto, Long recruiterId);
    Job findJob(Long jobId);

}
