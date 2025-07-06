package io.github.tundeadetunji.recruiter_service.repository;

import io.github.tundeadetunji.recruiter_service.domain.model.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter, Long> {
}
