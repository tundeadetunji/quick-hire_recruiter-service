package io.github.tundeadetunji.recruiter_service.dto;

import io.github.tundeadetunji.recruiter_service.domain.enums.Qualification;
import lombok.Data;

import java.util.List;

@Data
public class CandidateDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String password;
    private Qualification highestQualification;
    private List<String> additionalQualifications;
    private String resumeLink;
    private String jobTitle;
    private List<String> phones;
    private String email;
}
