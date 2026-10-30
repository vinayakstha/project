package com.project.project.dtos;

import com.project.project.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupResponseDto {
    private Long userId;
    private String username;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Role role;
}
