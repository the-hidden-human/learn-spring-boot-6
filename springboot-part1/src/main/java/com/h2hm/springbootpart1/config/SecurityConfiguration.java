package com.h2hm.springbootpart1.config;

import com.h2hm.springbootpart1.entity.Permission;
import com.h2hm.springbootpart1.entity.Role;
import com.h2hm.springbootpart1.entity.User.*;
import com.h2hm.springbootpart1.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpMethod.*;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        authorizeRequests -> {
                            authorizeRequests.requestMatchers(
                                    "/api/v1/auth/**",
                                    "/v2/api-docs",
                                    "/v3/api-docs",
                                    "/v3/api-docs/**",
                                    "/swagger-resources",
                                    "/swagger-resources/**",
                                    "/configuration/ui",
                                    "/configuration/security",
                                    "/swagger-ui/**",
                                    "/webjars/**",
                                    "/swagger-ui.html"
                            ).permitAll()
                            .requestMatchers("/api/v1/management/**").hasAnyRole(Role.ADMIN.name(), Role.MANAGER.name())
                            .requestMatchers(HttpMethod.GET, "/api/v1/management/**").hasAnyAuthority(Permission.ADMIN_READ.name(), Permission.MANAGER_READ.name())
                            .requestMatchers(HttpMethod.POST, "/api/v1/management/**").hasAnyAuthority(Permission.ADMIN_CREATE.name(), Permission.MANAGER_CREATE.name())
                            .requestMatchers(HttpMethod.PUT, "/api/v1/management/**").hasAnyAuthority(Permission.ADMIN_UPDATE.name(), Permission.MANAGER_UPDATE.name())
                            .requestMatchers(HttpMethod.DELETE, "/api/v1/management/**").hasAnyAuthority(Permission.ADMIN_DELETE.name(), Permission.MANAGER_DELETE.name()).anyRequest().authenticated();
                        })
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout( logout -> {
                    logout.logoutUrl("/api/v1/auth/logout");
                    logout.addLogoutHandler(logoutHandler);
                    logout.logoutSuccessHandler(((request, response, authentication) -> {
                        SecurityContextHolder.clearContext();
                    }));
                });
        return http.build();
    }

}
