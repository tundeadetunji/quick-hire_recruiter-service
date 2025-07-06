package io.github.tundeadetunji.recruiter_service.service.impl;

import io.github.tundeadetunji.recruiter_service.domain.model.CandidateApplication;
import io.github.tundeadetunji.recruiter_service.messaging.NotificationProducer;
import io.github.tundeadetunji.recruiter_service.shared.NotificationMessage;
import io.github.tundeadetunji.recruiter_service.strategy.JobStrategy;
import io.github.tundeadetunji.recruiter_service.dto.PostDto;
import io.github.tundeadetunji.recruiter_service.domain.enums.ApplicationStatus;
import io.github.tundeadetunji.recruiter_service.domain.enums.PostStatus;
import io.github.tundeadetunji.recruiter_service.domain.model.Job;
import io.github.tundeadetunji.recruiter_service.domain.model.Post;
import io.github.tundeadetunji.recruiter_service.strategy.PostStrategy;
import io.github.tundeadetunji.recruiter_service.exception.RecordNotFoundException;
import io.github.tundeadetunji.recruiter_service.exception.UnauthorizedAccessException;
import io.github.tundeadetunji.recruiter_service.service.PostService;
import io.github.tundeadetunji.recruiter_service.util.StringFunctions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.github.tundeadetunji.recruiter_service.constants.ExceptionMessages.*;
import static io.github.tundeadetunji.recruiter_service.constants.InlineStrings.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostStrategy postStrategy;
    private final JobStrategy jobStrategy;
    private final NotificationProducer notificationProducer;

    @Override
    public Post createJobPost(PostDto dto) {
        Job job = jobStrategy.findJobById(dto.getJobId())
                .orElseThrow(() -> new RecordNotFoundException(JOB_NOT_FOUND));

        Post post = postStrategy.saveJobPost(Post.from(job, job.getPoster().getId()));

        // Notify admin
        notificationProducer.notifyAdmin(NotificationMessage.builder()
                .subject(NEW_POST)
                .body("Post " + post.getId() + " just got created.")
                .build());

        //in prod, cascade to external job boards (e.g. LinkedIn)
        //by sending to a dedicated service, say
        //w/ retry, strategy pattern

        return post;
    }

    @Override
    public Post findJobPost(Long postId, Long recruiterId) {
        Post post = postStrategy.findJobPostById(postId)
                .orElseThrow(() -> new RecordNotFoundException(POST_NOT_FOUND));

        if (!post.getRecruiterId().equals(recruiterId)) {
            throw new UnauthorizedAccessException(UNAUTHORIZED_ACCESS);
        }

        return post;
    }

    @Override
    public Post changeJobPostStatus(Long postId, Long recruiterId, PostStatus status) {
        Post post = findJobPost(postId, recruiterId);
        PostStatus oldPostStatus = post.getPostStatus();
        post.setPostStatus(status);
        post = postStrategy.saveJobPost(post);

        // Notify admin
        notificationProducer.notifyAdmin(NotificationMessage.builder()
                .subject(JOB_POST_STATUS_CHANGE)
                .body("Job Post " + postId + " has changed from " + oldPostStatus + " to " + status +  ".")
                .build());

        return post;
    }

    @Override
    public Page<CandidateApplication> viewApplications(Long postId, ApplicationStatus status, Pageable pageable) {
        return postStrategy.findApplicationsByStatus(postId, status, pageable);

        /*
        //This assumes I'm not using Page
        Post post = postStrategy.findJobPostById(postId)
                .orElseThrow(() -> new RecordNotFoundException(POST_NOT_FOUND));

        return post.getApplications().stream()
                .filter(app -> app.getApplicationStatus() == status)
                .toList();*/
    }

    @Override
    public Post moveApplicationsToStatus(Long postId, ApplicationStatus status, List<CandidateApplication> applications) {
        Post post = postStrategy.findJobPostById(postId)
                .orElseThrow(() -> new RecordNotFoundException(POST_NOT_FOUND));

        post.getApplications().stream()
                .filter(app -> applications.contains(app))
                .forEach(app -> app.setApplicationStatus(status));

        post = postStrategy.saveJobPost(post);

        // Notify admin
        notificationProducer.notifyAdmin(NotificationMessage.builder()
                .subject(CANDIDATE_MOVEMENT)
                .body(StringFunctions.toPlural(applications.size(), "Candidate") + "from Job Post " + postId + " moved to " + status + ".")
                .build());
        return post;
    }

    @Override
    public Post updatePost(Post post) {
        Post saved = postStrategy.findJobPostById(post.getId())
                .orElseThrow(() -> new RecordNotFoundException(POST_NOT_FOUND));

        return postStrategy.saveJobPost(post);
    }

}
