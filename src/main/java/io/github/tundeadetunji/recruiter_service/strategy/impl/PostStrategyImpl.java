package io.github.tundeadetunji.recruiter_service.strategy.impl;

import io.github.tundeadetunji.recruiter_service.domain.enums.ApplicationStatus;
import io.github.tundeadetunji.recruiter_service.domain.enums.PostStatus;
import io.github.tundeadetunji.recruiter_service.domain.model.CandidateApplication;
import io.github.tundeadetunji.recruiter_service.domain.model.Post;
import io.github.tundeadetunji.recruiter_service.repository.CandidateApplicationRepository;
import io.github.tundeadetunji.recruiter_service.repository.PostRepository;
import io.github.tundeadetunji.recruiter_service.strategy.PostStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static io.github.tundeadetunji.recruiter_service.constants.ExceptionMessages.POST_NOT_FOUND;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Service
@RequiredArgsConstructor
public class PostStrategyImpl implements PostStrategy {

    private final PostRepository postRepository;
    private final CandidateApplicationRepository applicationRepository;

    @Override
    public Post saveJobPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Optional<Post> findJobPostById(Long postId) {
        return postRepository.findById(postId);
    }

    @Override
    public Post changeJobPostStatus(Long postId, PostStatus status) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException(POST_NOT_FOUND));
        post.setPostStatus(status);
        return postRepository.save(post);
    }

    @Override
    public Page<CandidateApplication> findApplicationsByStatus(Long postId, ApplicationStatus status, Pageable pageable) {

        return applicationRepository.findByPostIdAndApplicationStatus(postId, status, pageable);

        /*
        //This assumes not using Page
        Post post = repository.findById(postId)
                .orElseThrow(() -> new RuntimeException(POST_NOT_FOUND));

        List<CandidateApplication> result = new ArrayList<>();
        for (CandidateApplication app : post.getApplications()) {
            if (app.getApplicationStatus() == status) {
                result.add(app);
            }
        return result;
        }*/

    }

    @Override
    public Post addApplication(Long postId, CandidateApplication application) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException(POST_NOT_FOUND));

        post.getApplications().add(application);
        return postRepository.save(post);
    }

    @Override
    public Post moveApplicationsToStatus(Long postId, List<Long> applicationIds, ApplicationStatus newStatus) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException(POST_NOT_FOUND));

        post.getApplications().stream()
                .filter(app -> applicationIds.contains(app.getId()))
                .forEach(app -> app.setApplicationStatus(newStatus));

        return postRepository.save(post);
    }

    @Override
    public Post update(Post post) {
        return postRepository.save(post);
    }
}
