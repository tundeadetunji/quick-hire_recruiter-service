package io.github.tundeadetunji.recruiter_service.repository;

import io.github.tundeadetunji.recruiter_service.domain.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
