package io.github.tundeadetunji.recruiter_service.domain.model;

import io.github.tundeadetunji.recruiter_service.dto.CreateRecruiterDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Recruiter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String middleName;
    private String lastName;
    private String department;

    @ElementCollection
    private List<String> phones;

    private String email;

    public static Recruiter from(CreateRecruiterDto dto) {
        return Recruiter.builder()
                .firstName(dto.getFirstName())
                .middleName(dto.getMiddleName())
                .lastName(dto.getLastName())
                .department(dto.getDepartment())
                .phones(dto.getPhones())
                .email(dto.getEmail())
                .build();
    }
}
