package com.seungwooryu.woostagram.user.service;

import com.seungwooryu.woostagram.user.dto.SignupDto;
import com.seungwooryu.woostagram.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
class UserServiceTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void signupUser() {
        SignupDto request = new SignupDto("tmddn645@naver.com", "1123", "seungwooryu", "sksnnr12");
    }
}
