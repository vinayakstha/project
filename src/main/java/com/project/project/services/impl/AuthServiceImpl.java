package com.project.project.services.impl;

import com.project.project.dtos.LoginRequestDto;
import com.project.project.dtos.LoginResponseDto;
import com.project.project.dtos.SignupRequestDto;
import com.project.project.dtos.SignupResponseDto;
import com.project.project.entities.User;
import com.project.project.enums.Role;
import com.project.project.repositories.UserRepository;
import com.project.project.services.AuthService;
import com.project.project.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public SignupResponseDto signup(SignupRequestDto signupRequestDto) {
        if(userRepository.existsByEmail(signupRequestDto.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }

        if(userRepository.existsByUsername(signupRequestDto.getUsername())){
            throw new IllegalArgumentException("Username already exists");
        }

        User user = userRepository.save(User.builder()
                .username(signupRequestDto.getUsername())
                .fullName(signupRequestDto.getFullName())
                .email(signupRequestDto.getEmail())
                .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                .phoneNumber(signupRequestDto.getPhoneNumber())
                .role(Role.CUSTOMER)
                .build()
        );

        return SignupResponseDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .build();
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()
                )
        );

        String email = authentication.getName();
        String token = jwtService.generateToken(email);
        return new LoginResponseDto(token, "Bearer");
    }
}
