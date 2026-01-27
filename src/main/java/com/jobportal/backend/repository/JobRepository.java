package com.jobportal.backend.repository;

import com.jobportal.backend.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {

    Page<Job> findByStatusAndTitleContainingIgnoreCase(String status, String title, Pageable pageable);
}
