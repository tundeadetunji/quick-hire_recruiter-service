package io.github.tundeadetunji.recruiter_service.strategy;

import io.github.tundeadetunji.recruiter_service.domain.model.Recruiter;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

public interface RecruiterStrategy {
    Recruiter saveRecruiter(Recruiter recruiter);
    Optional<Recruiter> findRecruiterById(Long id);
    Page<Recruiter> findAllRecruiters(Pageable pageable);
}
