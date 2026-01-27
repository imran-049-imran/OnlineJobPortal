package com.jobportal.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JobRequest {

    @NotBlank
    private String title;

    private String description;

    private String location;

    private String jobType;

    private String salaryRange;

    private String experienceLevel;

    private String skills;

    private Long categoryId;

}
