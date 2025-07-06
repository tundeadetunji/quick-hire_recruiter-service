package io.github.tundeadetunji.recruiter_service.strategy.impl;

import io.github.tundeadetunji.recruiter_service.domain.model.Recruiter;
import io.github.tundeadetunji.recruiter_service.repository.RecruiterRepository;
import io.github.tundeadetunji.recruiter_service.strategy.RecruiterStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecruiterStrategyImpl implements RecruiterStrategy {

    private final RecruiterRepository repository;

    @Override
    public Recruiter saveRecruiter(Recruiter recruiter) {
        return repository.save(recruiter);
    }

    @Override
    public Optional<Recruiter> findRecruiterById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<Recruiter> findAllRecruiters(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
