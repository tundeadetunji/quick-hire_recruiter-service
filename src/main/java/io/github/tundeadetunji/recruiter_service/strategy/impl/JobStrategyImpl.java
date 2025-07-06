package io.github.tundeadetunji.recruiter_service.strategy.impl;

import io.github.tundeadetunji.recruiter_service.domain.model.Job;
import io.github.tundeadetunji.recruiter_service.repository.JobRepository;
import io.github.tundeadetunji.recruiter_service.strategy.JobStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobStrategyImpl implements JobStrategy {

    private final JobRepository repository;

    @Override
    public Job saveJob(Job job) {
        return repository.save(job);
    }

    @Override
    public Optional<Job> findJobById(Long jobId) {
        return repository.findById(jobId);
    }

    @Override
    public Job updateJob(Long jobId, Job job) {
        return repository.save(job);
    }
}
