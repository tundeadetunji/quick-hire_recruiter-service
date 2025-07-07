package io.github.tundeadetunji.recruiter_service.service.impl;

import io.github.tundeadetunji.recruiter_service.domain.enums.PostStatus;
import io.github.tundeadetunji.recruiter_service.domain.model.Job;
import io.github.tundeadetunji.recruiter_service.domain.model.Post;
import io.github.tundeadetunji.recruiter_service.dto.PostDto;
import io.github.tundeadetunji.recruiter_service.messaging.NotificationProducer;
import io.github.tundeadetunji.recruiter_service.shared.NotificationMessage;
import io.github.tundeadetunji.recruiter_service.strategy.JobStrategy;
import io.github.tundeadetunji.recruiter_service.strategy.PostStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock
    private JobStrategy jobStrategy;

    @Mock
    private PostStrategy postStrategy;

    @Mock
    private NotificationProducer notificationProducer;

    @InjectMocks
    private PostServiceImpl postService;

    @Test
    void createJobPost_shouldRollback_whenNotificationFails() {
        // Arrange
        Job job = Job.builder().id(1L).title("Backend Dev").build();
        Post unsavedPost = Post.from(job, 100L);
        Post savedPost = Post.from(job, 100L);
        savedPost.setId(10L);

        PostDto dto = PostDto.builder()
                .recruiterId(100L)
                .jobId(1L)
                .postStatus(PostStatus.ACTIVE)
                .build();

        when(jobStrategy.findJobById(1L)).thenReturn(Optional.of(job));
        when(postStrategy.saveJobPost(any(Post.class))).thenReturn(savedPost);
        doThrow(new RuntimeException("RabbitMQ failure"))
                .when(notificationProducer).notifyAdmin(any(NotificationMessage.class));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> postService.createJobPost(dto));

        verify(postStrategy).saveJobPost(any());
        verify(notificationProducer).notifyAdmin(any());
    }
}
