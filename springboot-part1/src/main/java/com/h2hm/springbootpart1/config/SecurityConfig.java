package com.h2hm.springbootpart1.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests( authorizeRequests ->
//                        authorizeRequests.anyRequest()
//                )
//    }

//    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;
//
//    private final AuthenticationProvider authenticationProvider;
//
//    public SecurityConfig(UserAuthenticationEntryPoint userAuthenticationEntryPoint, AuthenticationProvider authenticationProvider) {
//        this.userAuthenticationEntryPoint = userAuthenticationEntryPoint;
//        this.authenticationProvider = authenticationProvider;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
//        // http.exceptionHandling(new ExceptionHandling)
//    }
//
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint)
////                .and()
////                .addFilterBefore(new UsernamePasswordAuthFilter(authenticationProvider), BasicAuthenticationFilter.class)
////                .addFilterBefore(new JwtAuthFilter(authenticationProvider), UsernamePasswordAuthFilter)
////                .csrf().disable()
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                .and()
////                .authorizeHttpRequests((requests) -> requests
////                        .requestMatchers(HttpMethod.POST, "/v1/signIn", "/v1/signUp").permitAll()
////                        .anyRequest().authenticated())
////        ;
////        return http.build();
////    }

}
