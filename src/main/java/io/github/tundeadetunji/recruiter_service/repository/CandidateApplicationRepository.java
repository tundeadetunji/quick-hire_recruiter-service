package io.github.tundeadetunji.recruiter_service.repository;

import io.github.tundeadetunji.recruiter_service.domain.enums.ApplicationStatus;
import io.github.tundeadetunji.recruiter_service.domain.model.CandidateApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface CandidateApplicationRepository extends JpaRepository<CandidateApplication, Long> {
    Page<CandidateApplication> findByPostIdAndApplicationStatus(Long postId, ApplicationStatus status, Pageable pageable);

}
