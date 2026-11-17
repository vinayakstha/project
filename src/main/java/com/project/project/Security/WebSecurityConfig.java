package com.project.project.Security;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )

                .authorizeHttpRequests(auth -> auth

                        // Allow JSP forwards and error dispatches
                        .dispatcherTypeMatchers(
                                DispatcherType.FORWARD,
                                DispatcherType.ERROR
                        ).permitAll()

                        // Public pages
                        .requestMatchers(
                                "/", "/signup", "/login",
                                "/css/**", "/js/**", "/images/**"
                        ).permitAll()

                        // Authentication APIs
                        .requestMatchers("/auth/**").permitAll()

                        // Authenticated pages
                        .requestMatchers("/home", "/cart").authenticated()

                        // Cart APIs
                        .requestMatchers("/cart-api/**").authenticated()

                        // Category endpoints
                        .requestMatchers(HttpMethod.GET, "/category/**").permitAll()
                        .requestMatchers("/category/**").hasRole("ADMIN")

                        // Order endpoints
                        .requestMatchers(HttpMethod.GET, "/order").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/order/*/status").hasRole("ADMIN")

                        // Everything else
                        .anyRequest().authenticated()
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}