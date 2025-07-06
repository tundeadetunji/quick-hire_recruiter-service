package io.github.tundeadetunji.recruiter_service.service.impl;

import io.github.tundeadetunji.recruiter_service.domain.model.Job;
import io.github.tundeadetunji.recruiter_service.domain.model.Recruiter;
import io.github.tundeadetunji.recruiter_service.dto.CreateJobDto;
import io.github.tundeadetunji.recruiter_service.dto.UpdateJobDto;
import io.github.tundeadetunji.recruiter_service.exception.RecordNotFoundException;
import io.github.tundeadetunji.recruiter_service.exception.UnauthorizedAccessException;
import io.github.tundeadetunji.recruiter_service.messaging.NotificationProducer;
import io.github.tundeadetunji.recruiter_service.service.JobService;
import io.github.tundeadetunji.recruiter_service.shared.NotificationMessage;
import io.github.tundeadetunji.recruiter_service.strategy.JobStrategy;
import io.github.tundeadetunji.recruiter_service.strategy.RecruiterStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static io.github.tundeadetunji.recruiter_service.constants.ExceptionMessages.*;
import static io.github.tundeadetunji.recruiter_service.constants.InlineStrings.NEW_JOB;
import static io.github.tundeadetunji.recruiter_service.constants.InlineStrings.UPDATE_ON_JOB;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobStrategy jobStrategy;
    private final RecruiterStrategy recruiterStrategy;
    private final NotificationProducer notificationProducer;

    @Override
    public Job createJob(CreateJobDto dto) {
        Recruiter recruiter = recruiterStrategy.findRecruiterById(dto.getRecruiterId())
                .orElseThrow(() -> new RecordNotFoundException(RECRUITER_NOT_FOUND));

        Job job = jobStrategy.saveJob(Job.from(dto, recruiter));

        // Notify admin
        notificationProducer.notifyAdmin(NotificationMessage.builder()
                .subject(NEW_JOB)
                .body("Job " + job.getId() + " just got created.")
                .build());

        return job;
    }

    @Override
    public Job updateJob(Long jobId, UpdateJobDto dto, Long recruiterId) {
        Job job = jobStrategy.findJobById(jobId)
                .orElseThrow(() -> new RecordNotFoundException(JOB_NOT_FOUND));

        if (!job.getPoster().getId().equals(recruiterId)) {
            throw new UnauthorizedAccessException(UNAUTHORIZED_RECRUITER);
        }

        job.updateFrom(dto);

        job = jobStrategy.saveJob(job);

        // Notify admin
        notificationProducer.notifyAdmin(NotificationMessage.builder()
                .subject(UPDATE_ON_JOB)
                .body("Job " + job.getId() + " just got updated.")
                .build());


        return job;
    }

    @Override
    public Job findJob(Long jobId) {
        Job job = jobStrategy.findJobById(jobId)
                .orElseThrow(() -> new RecordNotFoundException(JOB_NOT_FOUND));

        return job;
    }

}
