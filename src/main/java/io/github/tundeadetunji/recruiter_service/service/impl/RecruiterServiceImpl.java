package io.github.tundeadetunji.recruiter_service.service.impl;

import io.github.tundeadetunji.recruiter_service.dto.CreateRecruiterDto;
import io.github.tundeadetunji.recruiter_service.domain.model.Recruiter;
import io.github.tundeadetunji.recruiter_service.messaging.NotificationProducer;
import io.github.tundeadetunji.recruiter_service.shared.NotificationMessage;
import io.github.tundeadetunji.recruiter_service.strategy.RecruiterStrategy;
import io.github.tundeadetunji.recruiter_service.exception.RecordNotFoundException;
import io.github.tundeadetunji.recruiter_service.service.RecruiterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.github.tundeadetunji.recruiter_service.constants.ExceptionMessages.RECRUITER_NOT_FOUND;
import static io.github.tundeadetunji.recruiter_service.constants.InlineStrings.NEW_RECRUITER;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecruiterServiceImpl implements RecruiterService {

    private final RecruiterStrategy recruiterStrategy;
    private final NotificationProducer notificationProducer;

    @Transactional
    @Override
    public Recruiter registerRecruiter(CreateRecruiterDto dto) {
        Recruiter recruiter = recruiterStrategy.saveRecruiter(Recruiter.from(dto));

        // Notify admin
        notificationProducer.notifyAdmin(NotificationMessage.builder()
                .subject(NEW_RECRUITER)
                .body("Recruiter " + dto.getFirstName() + " " + dto.getLastName() + " just got created.")
                .build());

        return recruiter;
    }

    @Override
    public Recruiter findRecruiter(Long id) {
        return recruiterStrategy.findRecruiterById(id)
                .orElseThrow(() -> new RecordNotFoundException(RECRUITER_NOT_FOUND));
    }

    @Override
    public Page<Recruiter> viewRecruiters(Pageable pageable) {
        return recruiterStrategy.findAllRecruiters(pageable);
    }
}

