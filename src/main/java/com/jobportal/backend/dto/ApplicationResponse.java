package com.jobportal.backend.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationResponse {

    private Long id;

    private String candidateName;
    private String candidateEmail;

    private String jobTitle;
    private String recruiterName;

    private String resumeUrl;
    private String status;

    private LocalDateTime appliedAt;
}
