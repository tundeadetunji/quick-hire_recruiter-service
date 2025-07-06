package io.github.tundeadetunji.recruiter_service.dto;

import io.github.tundeadetunji.recruiter_service.domain.enums.PostStatus;
import lombok.Data;

@Data
public class PostStatusUpdateDto {
    private PostStatus postStatus;
}

