package com.jobportal.backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JobResponse {

    private Long id;
    private String title;
    private String description;
    private String location;
    private String jobType;
    private String salaryRange;
    private String experienceLevel;
    private String skills;
    private String categoryName;
    private String recruiterName;
    private String status;
}
