package com.project.project.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Enter valid email address")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;
}
