package io.github.tundeadetunji.recruiter_service.domain.model;

import io.github.tundeadetunji.recruiter_service.dto.PostDto;
import io.github.tundeadetunji.recruiter_service.domain.enums.PostStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import java.time.LocalDateTime;
import java.util.*;

import static io.github.tundeadetunji.recruiter_service.constants.ExceptionMessages.CLONE_NOT_SUPPORTED;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Job job;

    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    private Long recruiterId;

    private LocalDateTime postedOn;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CandidateApplication> applications = new ArrayList<>();

    @Version
    private Long version;

    public static Post from(Job job, Long recruiterId) {
        return Post.builder()
                .job(job)
                .postStatus(PostStatus.ACTIVE)
                .recruiterId(recruiterId)
                .postedOn(LocalDateTime.now())
                .build();
    }
}
