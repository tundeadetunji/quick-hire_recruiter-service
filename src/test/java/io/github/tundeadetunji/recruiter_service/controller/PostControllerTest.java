package io.github.tundeadetunji.recruiter_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.tundeadetunji.recruiter_service.controller.PostController;
import io.github.tundeadetunji.recruiter_service.dto.PostDto;
import io.github.tundeadetunji.recruiter_service.domain.enums.PostStatus;
import io.github.tundeadetunji.recruiter_service.domain.model.Job;
import io.github.tundeadetunji.recruiter_service.domain.model.Post;
import io.github.tundeadetunji.recruiter_service.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    void createJobPost_shouldReturnCreatedPost() throws Exception {
        PostDto dto = PostDto.builder()
                .recruiterId(1L).jobId(2L).postStatus(PostStatus.ACTIVE)
                .applications(List.of()).build();

        Post post = Post.from(new Job(), 1L);
        post.setId(5L);

        when(postService.createJobPost(any(PostDto.class))).thenReturn(post);

        mockMvc.perform(post("/api/v1/post/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5L));

        verify(postService).createJobPost(any(PostDto.class));
    }
}
