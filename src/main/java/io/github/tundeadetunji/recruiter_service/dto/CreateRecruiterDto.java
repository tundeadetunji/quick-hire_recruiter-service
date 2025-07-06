package io.github.tundeadetunji.recruiter_service.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class CreateRecruiterDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private String department;
    private List<String> phones;
    private String email;
}
