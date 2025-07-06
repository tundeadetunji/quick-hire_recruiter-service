package io.github.tundeadetunji.recruiter_service.repository;

import io.github.tundeadetunji.recruiter_service.domain.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
