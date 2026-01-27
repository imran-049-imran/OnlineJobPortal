package com.jobportal.backend.service;

import com.jobportal.backend.dto.JobRequest;
import com.jobportal.backend.dto.JobResponse;
import com.jobportal.backend.entity.Job;
import com.jobportal.backend.entity.JobCategory;
import com.jobportal.backend.entity.Role;
import com.jobportal.backend.entity.User;
import com.jobportal.backend.repository.JobCategoryRepository;
import com.jobportal.backend.repository.JobRepository;
import com.jobportal.backend.repository.UserRepository;
import org.springframework.data.domain.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final JobCategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public JobService(JobRepository jobRepository,
                      JobCategoryRepository categoryRepository,
                      UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public JobResponse createJob(JobRequest request) {
        User recruiter = getCurrentUser();
        if (recruiter.getRole() != Role.RECRUITER && recruiter.getRole() != Role.ADMIN) {
            throw new RuntimeException("Access denied");
        }

        JobCategory category = null;
        if (request.getCategoryId() != null) {
            category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
        }

        Job job = Job.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .location(request.getLocation())
                .jobType(request.getJobType())
                .salaryRange(request.getSalaryRange())
                .experienceLevel(request.getExperienceLevel())
                .skills(request.getSkills())
                .category(category)
                .recruiter(recruiter)
                .status("OPEN")
                .build();

        jobRepository.save(job);
        return toResponse(job);
    }

    public Page<JobResponse> listJobs(String keyword, int page, int size) {
        // If you don't have createdAt in Job entity, do NOT sort by it
        Pageable pageable = PageRequest.of(page, size); // remove Sort.by("createdAt")

        String kw = (keyword == null) ? "" : keyword;

        Page<Job> jobsPage = jobRepository
                .findByStatusAndTitleContainingIgnoreCase("OPEN", kw, pageable);

        return jobsPage.map(this::toResponse);
    }

    public JobResponse getJob(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        return toResponse(job);
    }

    private JobResponse toResponse(Job job) {
        return JobResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .location(job.getLocation())
                .jobType(job.getJobType())
                .salaryRange(job.getSalaryRange())
                .experienceLevel(job.getExperienceLevel())
                .skills(job.getSkills())
                .categoryName(job.getCategory() != null ? job.getCategory().getName() : null)
                .recruiterName(job.getRecruiter() != null ? job.getRecruiter().getFullName() : null)
                .status(job.getStatus())
                .build();
    }
}
