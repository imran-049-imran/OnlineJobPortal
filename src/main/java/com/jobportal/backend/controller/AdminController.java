package com.jobportal.backend.controller;

import com.jobportal.backend.dto.ApplicationResponse;
import com.jobportal.backend.entity.ApplicationStatus;
import com.jobportal.backend.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin("*")
public class AdminController {

    private final AdminService adminService;

    // ===================== APPLICATIONS =====================

    // 🔹 All applied candidates (ADMIN PANEL)
    @GetMapping("/applications")
    public ResponseEntity<List<ApplicationResponse>> getAllApplications() {
        return ResponseEntity.ok(adminService.getAllApplications());
    }

    // 🔹 Update application status
    @PatchMapping("/applications/{id}/status")
    public ResponseEntity<?> updateApplicationStatus(
            @PathVariable Long id,
            @RequestParam ApplicationStatus status
    ) {
        adminService.updateApplicationStatus(id, status);
        return ResponseEntity.ok(Map.of("message", "Status updated"));
    }

    // 🔹 Delete application
    @DeleteMapping("/applications/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        adminService.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }

    // ===================== JOBS =====================

    // 🔹 All jobs in system
    @GetMapping("/jobs")
    public ResponseEntity<?> getAllJobs() {
        return ResponseEntity.ok(adminService.getAllJobs());
    }

    // 🔹 Close / Open job
    @PatchMapping("/jobs/{jobId}/status")
    public ResponseEntity<?> updateJobStatus(
            @PathVariable Long jobId,
            @RequestParam String status
    ) {
        adminService.updateJobStatus(jobId, status);
        return ResponseEntity.ok(Map.of("message", "Job status updated"));
    }
}
