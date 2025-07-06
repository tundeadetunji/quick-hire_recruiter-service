package io.github.tundeadetunji.recruiter_service.controller;


import io.github.tundeadetunji.recruiter_service.domain.model.Job;
import io.github.tundeadetunji.recruiter_service.dto.CreateJobDto;
import io.github.tundeadetunji.recruiter_service.dto.UpdateJobDto;
import io.github.tundeadetunji.recruiter_service.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job")
@RequiredArgsConstructor
public class JobController {

    private final JobService service;

    @Operation(summary = "Create a job")
    @PostMapping("/create")
    public ResponseEntity<Job> createJob(@Valid @RequestBody CreateJobDto dto) {
        return ResponseEntity.ok(service.createJob(dto));
    }

    @Operation(summary = "Update a job")
    @PutMapping("/job/{jobId}")
    public ResponseEntity<Job> updateJob(@PathVariable Long jobId,
                                         @RequestParam Long recruiterId,
                                         @Valid @RequestBody UpdateJobDto dto) {
        return ResponseEntity.ok(service.updateJob(jobId, dto, recruiterId));
    }

    @Operation(summary = "Find a specific job")
    @GetMapping("/job/{jobId}")
    public ResponseEntity<Job> findJob(@PathVariable Long jobId) {
        return ResponseEntity.ok(service.findJob(jobId));
    }

}

