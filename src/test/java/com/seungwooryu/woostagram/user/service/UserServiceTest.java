package com.seungwooryu.woostagram.user.service;

import com.seungwooryu.woostagram.user.dto.SigninDto;
import com.seungwooryu.woostagram.user.dto.UserDto;
import com.seungwooryu.woostagram.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    @DisplayName("로그인 성공")
    void signinUser() {
        //given
        String email = "tmddn645@naver.com";
        String password = "vvee12";

        SigninDto signinDto = new SigninDto();
        signinDto.setEmail(email);
        signinDto.setPassword(password);

        //when
        UserDto userDto = userService.signin(signinDto);

        //then
        assertThat(userDto.getEmail()).isEqualTo(email);
        assertThat(userDto.getPassword()).isEqualTo(password);
    }
}
