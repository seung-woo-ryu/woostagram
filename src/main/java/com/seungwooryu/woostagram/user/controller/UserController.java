package com.seungwooryu.woostagram.user.controller;

import com.seungwooryu.woostagram.common.utils.ApiUtils.ApiResult;
import com.seungwooryu.woostagram.user.dto.SigninDto;
import com.seungwooryu.woostagram.user.dto.SignupDto;
import com.seungwooryu.woostagram.user.dto.UserDto;
import com.seungwooryu.woostagram.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;

import static com.seungwooryu.woostagram.common.utils.ApiUtils.success;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResult<?>> signup(@Valid @RequestBody SignupDto signupDto) {
        // 회원가입 성공한 유저 정보로 /login으로 포워딩.
        final ApiResult<String> response = success(new ArrayList<>());
        userService.signup(signupDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResult<?>> signin(@Valid @RequestBody SigninDto signinDto, HttpServletRequest request) {
        // loginUser 존재확인
        UserDto loginUserDto = userService.signin(signinDto);

        final ApiResult<String> response = success(new ArrayList<>());
        // Session 생성해서 유저 정보 저장.
        HttpSession session = request.getSession();
        session.setAttribute("USER", loginUserDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

