package io.github.tundeadetunji.recruiter_service.service;

import io.github.tundeadetunji.recruiter_service.domain.model.Recruiter;
import io.github.tundeadetunji.recruiter_service.dto.CreateRecruiterDto;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface RecruiterService {
    Recruiter registerRecruiter(CreateRecruiterDto dto);
    Recruiter findRecruiter(Long id);
    Page<Recruiter> viewRecruiters(Pageable pageable);
}
