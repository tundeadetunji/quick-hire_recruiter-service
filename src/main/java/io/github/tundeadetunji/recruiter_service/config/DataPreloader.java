package io.github.tundeadetunji.recruiter_service.config;

import io.github.tundeadetunji.recruiter_service.domain.enums.PostStatus;
import io.github.tundeadetunji.recruiter_service.domain.model.Job;
import io.github.tundeadetunji.recruiter_service.domain.model.Post;
import io.github.tundeadetunji.recruiter_service.domain.model.Recruiter;
import io.github.tundeadetunji.recruiter_service.dto.CreateJobDto;
import io.github.tundeadetunji.recruiter_service.dto.CreateRecruiterDto;
import io.github.tundeadetunji.recruiter_service.dto.PostDto;
import io.github.tundeadetunji.recruiter_service.service.JobService;
import io.github.tundeadetunji.recruiter_service.service.PostService;
import io.github.tundeadetunji.recruiter_service.service.RecruiterService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataPreloader {

    /*private final RecruiterService recruiterService;
    private final JobService jobService;
    private final PostService postService;*/

    @PostConstruct
    public void preloadData() {
        //for dev only

        // Recruiter 1
        /*Recruiter r1 = recruiterService.registerRecruiter(CreateRecruiterDto.builder()
                .firstName("Aisha").middleName("Oluwatoyin").lastName("Suleiman")
                .email("aisha.suleiman@quickhire.com").phones(List.of("+2348012345678"))
                .department("Tech").build());

        Job j1 = jobService.createJob(CreateJobDto.builder()
                .recruiterId(r1.getId()).title("Java Backend Engineer")
                .mandatoryRequirement("Java + Spring Boot")
                .desirableRequirement("Docker, PostgreSQL")
                .minimumSalary("₦400,000")
                .description("Work on microservices backend systems.").build());

        Post p1 = postService.createJobPost(PostDto.builder()
                .recruiterId(r1.getId()).jobId(j1.getId())
                .postStatus(PostStatus.ACTIVE)
                .applications(List.of()).build());*/

        // Recruiter 2
        /*Recruiter r2 = recruiterService.registerRecruiter(CreateRecruiterDto.builder()
                .firstName("Emeka").middleName("John").lastName("Okafor")
                .email("emeka.okafor@quickhire.com").phones(List.of("+2348023456789"))
                .department("Design").build());

        Job j2 = jobService.createJob(CreateJobDto.builder()
                .recruiterId(r2.getId()).title("UI/UX Designer")
                .mandatoryRequirement("Figma + Design Thinking")
                .desirableRequirement("Prototyping tools")
                .minimumSalary("₦300,000")
                .description("Work on product design and user flows.").build());

        Post p2 = postService.createJobPost(PostDto.builder()
                .recruiterId(r2.getId()).jobId(j2.getId())
                .postStatus(PostStatus.ACTIVE)
                .applications(List.of()).build());

        System.out.println("Recruiters, jobs, and posts preloaded.");*/
    }
}
