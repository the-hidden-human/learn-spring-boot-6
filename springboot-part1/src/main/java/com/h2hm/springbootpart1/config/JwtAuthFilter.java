package com.h2hm.springbootpart1.config;

import com.h2hm.springbootpart1.constant.SecurityConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserAuthenticationProvider userAuthenticationProvider;

    public JwtAuthFilter(UserAuthenticationProvider userAuthenticationProvider) {
        this.userAuthenticationProvider = userAuthenticationProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(header != null) {
            String[] authElements = header.split(StringUtils.SPACE);

            if(authElements.length == 2 && SecurityConstants.BEARER.equals(authElements[0])) {
                try {
                    SecurityContextHolder.getContext().setAuthentication(userAuthenticationProvider.validateToken(authElements[1]));
                } catch (Exception exception) {
                    SecurityContextHolder.clearContext();
                    throw exception;
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
