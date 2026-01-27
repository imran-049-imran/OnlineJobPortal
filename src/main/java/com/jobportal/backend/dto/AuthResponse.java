package com.jobportal.backend.dto;


import com.jobportal.backend.entity.Role;
import lombok.*;

@Data
@AllArgsConstructor
@Builder
@Setter
@Getter
public class AuthResponse {

    private String token;
    private Long userId;
    private String fullName;
    private String email;
    private Role role;


}
