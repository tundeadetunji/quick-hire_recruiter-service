package io.github.tundeadetunji.recruiter_service.dto;

import io.github.tundeadetunji.recruiter_service.domain.enums.ApplicationStatus;
import lombok.Data;

@Data
public class CandidateApplicationDto {
    private Long id;
    private Long candidateId;
    private Long postId;
    private Long jobId; // left for display convenience
    private ApplicationStatus applicationStatus;
}
