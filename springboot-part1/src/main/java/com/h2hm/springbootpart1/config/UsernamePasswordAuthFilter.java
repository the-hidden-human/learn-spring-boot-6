package com.h2hm.springbootpart1.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.h2hm.springbootpart1.dto.CredentialsDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    // private final UserAuthenticationPr

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if("/v1/signIn".equals(request.getServletPath()) && HttpMethod.PATCH.matches(request.getMethod())) {
            CredentialsDto credentialsDto = MAPPER.readValue(request.getInputStream(), CredentialsDto.class);

            try {

            } catch (RuntimeException exception) {
                SecurityContextHolder.clearContext();
                throw exception;
            }
        }
        filterChain.doFilter(request, response);
    }
}
