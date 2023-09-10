package com.h2hm.springbootpart1.service;

import com.h2hm.springbootpart1.dto.CredentialsDto;
import com.h2hm.springbootpart1.dto.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;

@Service
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto authenticate(CredentialsDto credentialsDto) {
        String encodeMasterPassword = passwordEncoder.encode(CharBuffer.wrap("the-h2hm"));
        if(passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), encodeMasterPassword)){
            return new UserDto(1L, "Sergio", "Lema", "login", "token");
        }
        throw new RuntimeException("Invalid Password");
    }

    public UserDto findByLogin(String login) {
        if ("login".equals(login)) {
            return new UserDto(1L, "Sergio", "Lema", "login", "token");
        }
        throw new RuntimeException("Invalid Login");
    }
}
