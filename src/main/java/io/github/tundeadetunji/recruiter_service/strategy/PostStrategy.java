package io.github.tundeadetunji.recruiter_service.strategy;

import io.github.tundeadetunji.recruiter_service.domain.enums.ApplicationStatus;
import io.github.tundeadetunji.recruiter_service.domain.enums.PostStatus;
import io.github.tundeadetunji.recruiter_service.domain.model.CandidateApplication;
import io.github.tundeadetunji.recruiter_service.domain.model.Post;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface PostStrategy {
    Post saveJobPost(Post post);
    Optional<Post> findJobPostById(Long postId);
    Post changeJobPostStatus(Long postId, PostStatus status);
    Page<CandidateApplication> findApplicationsByStatus(Long postId, ApplicationStatus status, Pageable pageable);
    Post addApplication(Long postId, CandidateApplication application);
    Post moveApplicationsToStatus(Long postId, List<Long> applicationIds, ApplicationStatus newStatus);
    Post update(Post post);

}

