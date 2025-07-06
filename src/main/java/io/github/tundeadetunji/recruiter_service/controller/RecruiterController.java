package io.github.tundeadetunji.recruiter_service.controller;

import io.github.tundeadetunji.recruiter_service.domain.model.Recruiter;
import io.github.tundeadetunji.recruiter_service.dto.CreateRecruiterDto;
import io.github.tundeadetunji.recruiter_service.service.RecruiterService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recruiter")
@RequiredArgsConstructor
public class RecruiterController {

    private final RecruiterService service;

    @Operation(summary = "Register a new recruiter")
    @PostMapping
    public ResponseEntity<Recruiter> registerRecruiter(@Valid @RequestBody CreateRecruiterDto dto) {
        return ResponseEntity.ok(service.registerRecruiter(dto));
    }

    @Operation(summary = "Get all recruiters")
    @GetMapping
    public ResponseEntity<List<Recruiter>> viewRecruiters(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.viewRecruiters(PageRequest.of(page, size)).getContent());
    }

    @Operation(summary = "Find recruiter by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Recruiter> findRecruiter(@PathVariable Long id) {
        return ResponseEntity.ok(service.findRecruiter(id));
    }
}
