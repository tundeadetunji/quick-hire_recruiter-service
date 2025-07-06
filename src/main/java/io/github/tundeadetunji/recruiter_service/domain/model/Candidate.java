package io.github.tundeadetunji.recruiter_service.domain.model;

import io.github.tundeadetunji.recruiter_service.dto.CandidateDto;
import io.github.tundeadetunji.recruiter_service.domain.enums.Qualification;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static io.github.tundeadetunji.recruiter_service.constants.ExceptionMessages.CLONE_NOT_SUPPORTED;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Qualification highestQualification;

    @ElementCollection
    private List<String> additionalQualifications;

    private String resumeLink;
    private String jobTitle;

    @ElementCollection
    private List<String> phones;

    private String email;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CandidateApplication> applications = new ArrayList<>();


    public static Candidate from(CandidateDto dto){
        return Candidate.builder()
                .firstName(dto.getFirstName())
                .middleName(dto.getMiddleName())
                .lastName(dto.getLastName())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .highestQualification(dto.getHighestQualification())
                .additionalQualifications(dto.getAdditionalQualifications())
                .resumeLink(dto.getResumeLink())
                .jobTitle(dto.getJobTitle())
                .phones(dto.getPhones())
                .email(dto.getEmail())
                .build();
    }

}
