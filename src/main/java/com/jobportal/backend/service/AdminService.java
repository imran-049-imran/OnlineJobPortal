package com.jobportal.backend.service;

import com.jobportal.backend.dto.ApplicationResponse;
import com.jobportal.backend.entity.Application;
import com.jobportal.backend.entity.ApplicationStatus;
import com.jobportal.backend.repository.ApplicationRepository;
import com.jobportal.backend.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;

    public List<ApplicationResponse> getAllApplications() {
        return applicationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public void updateApplicationStatus(Long id, ApplicationStatus status) {
        Application app = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        app.setStatus(status);
        applicationRepository.save(app);
    }

    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }

    public Object getAllJobs() {
        return jobRepository.findAll();
    }

    public void updateJobStatus(Long jobId, String status) {
        var job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        job.setStatus(status);
        jobRepository.save(job);
    }

    private ApplicationResponse mapToResponse(Application app) {
        ApplicationResponse res = new ApplicationResponse();
        res.setId(app.getId());
        res.setCandidateName(app.getCandidate().getFullName());
        res.setCandidateEmail(app.getCandidate().getEmail());
        res.setJobTitle(app.getJob().getTitle());
        res.setRecruiterName(app.getJob().getRecruiter().getFullName());
        res.setResumeUrl(app.getResumeUrl());
        res.setStatus(app.getStatus().name());
        res.setAppliedAt(app.getAppliedAt());
        return res;
    }
}
