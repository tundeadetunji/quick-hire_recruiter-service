package io.github.tundeadetunji.recruiter_service.service;

import io.github.tundeadetunji.recruiter_service.domain.enums.ApplicationStatus;
import io.github.tundeadetunji.recruiter_service.domain.enums.PostStatus;
import io.github.tundeadetunji.recruiter_service.domain.model.CandidateApplication;
import io.github.tundeadetunji.recruiter_service.domain.model.Post;
import io.github.tundeadetunji.recruiter_service.dto.PostDto;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface PostService {
    Post createJobPost(PostDto dto);
    Post findJobPost(Long postId, Long recruiterId);
    Post changeJobPostStatus(Long postId, Long recruiterId, PostStatus status);

    Page<CandidateApplication> viewApplications(Long postId, ApplicationStatus status, Pageable pageable);
    Post moveApplicationsToStatus(Long postId, ApplicationStatus status, List<CandidateApplication> applications);
    Post updatePost(Post post);

}
