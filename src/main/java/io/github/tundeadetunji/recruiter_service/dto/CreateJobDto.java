package io.github.tundeadetunji.recruiter_service.dto;

import io.github.tundeadetunji.recruiter_service.constants.DtoMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateJobDto {
    @NotNull(message = DtoMessages.MESSAGE_NULL_VALUE_PROVIDED)
    @Positive(message = DtoMessages.MESSAGE_ID_NOT_POSITIVE)
    private Long recruiterId;
    @NotBlank(message = DtoMessages.MESSAGE_TITLE_NOT_PROVIDED)
    private String title;
    private String mandatoryRequirement;
    private String desirableRequirement;
    private String minimumSalary;
    @NotBlank(message = DtoMessages.MESSAGE_DESCRIPTION_NOT_PROVIDED)
    private String description;
}
