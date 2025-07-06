package io.github.tundeadetunji.recruiter_service.dto;

import io.github.tundeadetunji.recruiter_service.domain.enums.PostStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PostDto {
    private Long id;
    private Long recruiterId;
    private Long jobId;
    private PostStatus postStatus;
    private LocalDateTime postedOn;
    private List<CandidateApplicationDto> applications;
}
