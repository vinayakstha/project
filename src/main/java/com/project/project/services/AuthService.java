package com.project.project.services;

import com.project.project.dtos.SignupRequestDto;
import com.project.project.dtos.SignupResponseDto;

public interface AuthService {
    SignupResponseDto signup(SignupRequestDto signupRequestDto);
}
