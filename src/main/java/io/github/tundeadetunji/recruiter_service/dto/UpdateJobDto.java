package io.github.tundeadetunji.recruiter_service.dto;

import io.github.tundeadetunji.recruiter_service.constants.DtoMessages;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateJobDto {
    @NotBlank(message = DtoMessages.MESSAGE_TITLE_NOT_PROVIDED)
    private String title;
    private String mandatoryRequirement;
    private String desirableRequirement;
    private String minimumSalary;
    @NotBlank(message = DtoMessages.MESSAGE_DESCRIPTION_NOT_PROVIDED)
    private String description;
}
