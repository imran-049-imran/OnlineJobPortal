//package com.jobportal.backend.service;
//
//import com.jobportal.backend.dto.ApplicationRequest;
//import com.jobportal.backend.dto.ApplicationResponse;
//import com.jobportal.backend.entity.Job;
//import com.jobportal.backend.entity.JobApplication;
//import com.jobportal.backend.entity.Role;
//import com.jobportal.backend.entity.User;
//import com.jobportal.backend.exception.BadRequestException;
//import com.jobportal.backend.exception.NotFoundException;
//import com.jobportal.backend.repository.JobApplicationRepository;
//import com.jobportal.backend.repository.JobRepository;
//import com.jobportal.backend.repository.UserRepository;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//
//@Service
//public class JobApplicationService {
//
//    private final JobRepository jobRepository;
//    private final JobApplicationRepository jobApplicationRepository;
//    private final UserRepository userRepository;
//
//    public JobApplicationService(JobRepository jobRepository,
//                                 JobApplicationRepository jobApplicationRepository,
//                                 UserRepository userRepository) {
//        this.jobRepository = jobRepository;
//        this.jobApplicationRepository = jobApplicationRepository;
//        this.userRepository = userRepository;
//    }
//
//    private User getCurrentUser() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String email = auth.getName();
//        return userRepository.findByEmail(email)
//                .orElseThrow(() -> new NotFoundException("User not found"));
//    }
//
//    public ApplicationResponse apply(ApplicationRequest request) {
//        User current = getCurrentUser();
//        if (current.getRole() != Role.CANDIDATE) {
//            throw new com.jobportal.backend.exception.BadRequestException("Only candidates can apply to jobs");
//        }
//
//        Job job = jobRepository.findById(request.getJobId())
//                .orElseThrow(() -> new NotFoundException("Job not found"));
//
//        if (!"OPEN".equalsIgnoreCase(job.getStatus())) {
//            throw new com.jobportal.backend.exception.BadRequestException("Job is not open");
//        }
//
//        // prevent duplicate applications (optional)
//        List<JobApplication> existing = jobApplicationRepository.findByCandidate(current);
//        boolean alreadyApplied = existing.stream()
//                .anyMatch(a -> a.getJob().getId().equals(job.getId()));
//        if (alreadyApplied) {
//            throw new com.jobportal.backend.exception.BadRequestException("Already applied to this job");
//        }
//
//        JobApplication application = JobApplication.builder()
//                .job(job)
//                .candidate(current)
//                .resumeUrl(request.getResumeUrl())
//                .status("APPLIED")
//                .build();
//
//        jobApplicationRepository.save(application);
//        return toResponse(application);
//    }
//
//    public List<ApplicationResponse> myApplications() {
//        User current = getCurrentUser();
//        List<JobApplication> list = jobApplicationRepository.findByCandidate(current);
//        return list.stream().map(this::toResponse).toList();
//    }
//
//    public List<ApplicationResponse> applicationsForJob(Long jobId) {
//        User current = getCurrentUser();
//        Job job = jobRepository.findById(jobId)
//                .orElseThrow(() -> new NotFoundException("Job not found"));
//
//        if (!job.getRecruiter().getId().equals(current.getId())
//                && current.getRole() != Role.ADMIN) {
//            throw new BadRequestException("Not allowed to view applications for this job");
//        }
//
//        List<JobApplication> list = jobApplicationRepository.findByJob(job);
//        return list.stream().map(this::toResponse).toList();
//    }
//
//    private ApplicationResponse toResponse(JobApplication app) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        return ApplicationResponse.builder()
//                .id(app.getId())
//                .jobId(app.getJob().getId())
//                .jobTitle(app.getJob().getTitle())
//                .candidateId(app.getCandidate().getId())
//                .candidateName(app.getCandidate().getFullName())
//                .resumeUrl(app.getResumeUrl())
//                .status(app.getStatus())
//                .appliedAt(app.getAppliedAt() != null ? app.getAppliedAt().format(formatter) : null)
//                .build();
//    }
//}
