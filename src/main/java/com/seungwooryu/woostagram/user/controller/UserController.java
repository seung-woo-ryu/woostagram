package com.seungwooryu.woostagram.user.controller;

import com.seungwooryu.woostagram.common.utils.ApiUtils.ApiResult;
import com.seungwooryu.woostagram.user.dto.*;
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

import static com.seungwooryu.woostagram.common.utils.ApiUtils.success;
import static com.seungwooryu.woostagram.common.utils.Constants.LOGGED_IN_USER_SESSION_KEY;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResult<?>> signup(@Valid @RequestBody SignupDto signupDto) {
        userService.signup(signupDto);
        return new ResponseEntity<>(success(), HttpStatus.OK);
    }

    @PostMapping("/signup/email")
    public ResponseEntity<ApiResult<?>> checkDuplicateEmail(@Valid @RequestBody EmailDto emailDto) {
        final boolean isDuplicatedEmail = userService.checkDuplicationEmail(emailDto);
        return new ResponseEntity<>(success(isDuplicatedEmail), HttpStatus.OK);
    }

    @PostMapping("/signup/nickname")
    public ResponseEntity<ApiResult<?>> checkDuplicateNickname(@Valid @RequestBody NicknameDto nicknameDto) {
        final boolean isDuplicatedNickname = userService.checkDuplicationNickname(nicknameDto);
        return new ResponseEntity<>(success(isDuplicatedNickname), HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResult<?>> signin(@Valid @RequestBody SigninDto signinDto, HttpServletRequest request) {
        // loginUser 존재확인
        UserDto loginUserDto = userService.signin(signinDto);

        // Session 생성해서 유저 정보 저장.
        HttpSession session = request.getSession();
        session.setAttribute(LOGGED_IN_USER_SESSION_KEY, loginUserDto);

        return new ResponseEntity<>(success(), HttpStatus.OK);
    }
}

