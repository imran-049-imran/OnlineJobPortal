package com.jobportal.backend.controller;


import com.jobportal.backend.dto.ApplicationRequest;
import com.jobportal.backend.dto.ApplicationResponse;
import com.jobportal.backend.service.JobApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "http://localhost:5173")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    // Candidate applies for a job
    @PostMapping
    public ResponseEntity<ApplicationResponse> apply(@Valid @RequestBody ApplicationRequest request) {
        return ResponseEntity.ok(jobApplicationService.apply(request));
    }

    // Candidate: list my applications
    @GetMapping("/me")
    public ResponseEntity<List<ApplicationResponse>> myApplications() {
        return ResponseEntity.ok(jobApplicationService.myApplications());
    }

    // Recruiter: list applications for a job they posted
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<ApplicationResponse>> applicationsForJob(@PathVariable Long jobId) {
        return ResponseEntity.ok(jobApplicationService.applicationsForJob(jobId));
    }
}

