package io.github.tundeadetunji.recruiter_service.controller;

import io.github.tundeadetunji.recruiter_service.domain.enums.ApplicationStatus;
import io.github.tundeadetunji.recruiter_service.domain.enums.PostStatus;
import io.github.tundeadetunji.recruiter_service.domain.model.CandidateApplication;
import io.github.tundeadetunji.recruiter_service.domain.model.Post;
import io.github.tundeadetunji.recruiter_service.dto.PostDto;
import io.github.tundeadetunji.recruiter_service.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService service;

    @Operation(summary = "Creates a job post")
    @PostMapping("/post")
    public ResponseEntity<Post> createJobPost(@RequestBody PostDto dto) {
        return ResponseEntity.ok(service.createJobPost(dto));
    }

    @Operation(summary = "Find job post by ID for recruiter")
    @GetMapping("/post/{postId}")
    public ResponseEntity<Post> findJobPost(@PathVariable Long postId,
                                            @RequestParam Long recruiterId) {
        return ResponseEntity.ok(service.findJobPost(postId, recruiterId));
    }

    @Operation(summary = "Change job post status")
    @PatchMapping("/post/{postId}/status")
    public ResponseEntity<Post> updateJobPostStatus(@PathVariable Long postId,
                                                    @RequestParam Long recruiterId,
                                                    @RequestParam PostStatus status) {
        return ResponseEntity.ok(service.changeJobPostStatus(postId, recruiterId, status));
    }

    @Operation(summary = "Get all applications for a candidate")
    @GetMapping("/post/{postId}/applications")
    public ResponseEntity<List<CandidateApplication>> viewApplications(@PathVariable Long postId,
                                                                       @RequestParam ApplicationStatus status,
                                                                       @RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.viewApplications(postId, status, pageable).getContent());    }

    @Operation(summary = "Change candidates' ApplicationStatus")
    @PutMapping("/post/{postId}/applications/status")
    public ResponseEntity<Post> moveApplications(@PathVariable Long postId,
                                                 @RequestParam ApplicationStatus status,
                                                 @RequestBody List<CandidateApplication> applications) {
        return ResponseEntity.ok(service.moveApplicationsToStatus(postId, status, applications));
    }

}

