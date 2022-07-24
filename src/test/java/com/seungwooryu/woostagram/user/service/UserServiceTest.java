package com.seungwooryu.woostagram.user.service;

import com.seungwooryu.woostagram.user.dto.SignupDto;
import com.seungwooryu.woostagram.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootApplication
class UserServiceTest {
    @Autowired
    BindingResult bindingResult;
    @Autowired
    UserRepository userRepository;
    //UserService userService = new UserService(userRepository, bindingResult);

    @Test
    void signupUser() {
        SignupDto request = new SignupDto("tmddn645@naver.com","1123","seungwooryu","sksnnr12");
        //userService.signupUser(request);
    }
}