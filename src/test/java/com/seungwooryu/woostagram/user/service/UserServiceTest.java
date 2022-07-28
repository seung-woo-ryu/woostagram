package com.seungwooryu.woostagram.user.service;

import com.seungwooryu.woostagram.user.dto.SigninDto;
import com.seungwooryu.woostagram.user.dto.SignupDto;
import com.seungwooryu.woostagram.user.dto.UserDto;
import com.seungwooryu.woostagram.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootApplication
class UserServiceTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    @Transactional
    void signupUser() {
        SignupDto request = new SignupDto("tmddn645@naver.com", "1123", "seungwooryu", "sksnnr12");
        //userService.signupUser(request);
    }

    @Test
    @DisplayName("로그인 성공")
    void signinUser() {
        //given
        String email = "tmddn645@naver.com";
        String password = "vvee12";

        SigninDto signinDto = new SigninDto(email, password);
        //when
        UserDto userDto = userService.signinUser(signinDto);

        //then
        assertThat(userDto.getEmail()).isEqualTo(email);
        assertThat(userDto.getEmail()).isEqualTo(password);
    }

    @Test
    @DisplayName("로그인 성공")
    void signinUser() {
        //given
        String email = "tmddn645@naver.com";
        String password = "vvee12";

        SigninDto signinDto = new SigninDto(email, password);
        //when
        UserDto userDto = userService.signinUser(signinDto);

        //then
        assertThat(userDto.getEmail()).isEqualTo(email);
        assertThat(userDto.getEmail()).isEqualTo(password);
    }
}