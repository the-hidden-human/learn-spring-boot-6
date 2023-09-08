package com.h2hm.springbootpart1.service;

import com.h2hm.springbootpart1.entity.UserInfo;
import com.h2hm.springbootpart1.repository.UserInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record UserService(UserInfoRepository repository, PasswordEncoder passwordEncoder) {

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        log.info("Them user thanh cong");
        return "Them user thanh cong";
    }
}
