package com.jobportal.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApplicationRequest {

    @NotNull
    private Long jobId;
    private String resumeUrl;
}
