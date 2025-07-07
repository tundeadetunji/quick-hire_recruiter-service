package io.github.tundeadetunji.recruiter_service.domain.model;

import io.github.tundeadetunji.recruiter_service.dto.CreateJobDto;
import io.github.tundeadetunji.recruiter_service.dto.UpdateJobDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Recruiter poster;

    private String title;
    private String mandatoryRequirement;
    private String desirableRequirement;
    private String minimumSalary;
    private String description;

    @Version
    private Long version;

    public static Job from(CreateJobDto dto, Recruiter recruiter) {
        return Job.builder()
                .title(dto.getTitle())
                .mandatoryRequirement(dto.getMandatoryRequirement())
                .desirableRequirement(dto.getDesirableRequirement())
                .minimumSalary(dto.getMinimumSalary())
                .description(dto.getDescription())
                .poster(recruiter)
                .build();
    }

    public void updateFrom(UpdateJobDto dto) {
        this.title = dto.getTitle();
        this.mandatoryRequirement = dto.getMandatoryRequirement();
        this.desirableRequirement = dto.getDesirableRequirement();
        this.minimumSalary = dto.getMinimumSalary();
        this.description = dto.getDescription();
    }
}
