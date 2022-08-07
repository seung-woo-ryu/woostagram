package com.seungwooryu.woostagram.user.controller;

import com.seungwooryu.woostagram.AbstractControllerTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

class UserControllerTest extends AbstractControllerTests {
    @Test
    @DisplayName("로그인 성공.")
    void signin_success_isOk() {
        Map<String, String> params = new HashMap<>();
        params.put("email", "tmddn645@naver.com");
        params.put("password", "vvee12");

        clearCookie();
        postJsonRequest("/signin", params)
                .expectHeader()
                .exists(HttpHeaders.SET_COOKIE)

                .expectCookie()
                .exists("JSESSIONID")

                .expectStatus()
                .isOk()

                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.response").isEmpty()
                .jsonPath("$.error").doesNotExist();

    }

    @Test
    @DisplayName("로그인 실패. 유효하지 않는 이메일")
    void signin_fail_badRequest() {
        Map<String, String> params = new HashMap<>();
        params.put("email", "tmddn641com");
        params.put("password", "vvee12");

        clearCookie();
        postJsonRequest("/signin", params)

                .expectStatus()
                .isBadRequest()

                .expectBody()
                .jsonPath("$.success").isEqualTo(false)
                .jsonPath("$.response").isEmpty()
                .jsonPath("$.error.status").isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("회원가입 성공.")
    void signup_success_isOk() {
        Map<String, String> params = new HashMap<>();
        params.put("email", "asdf1234@naver.com");
        params.put("nickname", "asdfqwer");
        params.put("password", "vvee12");
        params.put("name", "seungwoo");

        clearCookie();
        postJsonRequest("/signup", params)

                .expectStatus()
                .isOk()

                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.response").isEmpty()
                .jsonPath("$.error").doesNotExist();
    }

    @Test
    @DisplayName("회원가입 실패. 이메일 중복")
    void signup_fail_badRequest() {
        Map<String, String> params = new HashMap<>();
        params.put("email", "tmddn645@naver.com");
        params.put("nickname", "asdfqwer");
        params.put("password", "vvee12");
        params.put("name", "seungwoo");

        clearCookie();
        postJsonRequest("/signup", params)

                .expectStatus()
                .isBadRequest()

                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.success").isEqualTo(false)
                .jsonPath("$.response").isEmpty()
                .jsonPath("$.error.status").isEqualTo(HttpStatus.BAD_REQUEST.value())
                .jsonPath("$.error.message").isEqualTo("중복된 이메일입니다");
    }

    @Test
    @DisplayName("이메일 중복체크 성공")
    void checkDuplicateEmail_succes_isOk() {
        Map<String, String> params = new HashMap<>();
        params.put("email", "tmddn645@naver.com");

        clearCookie();
        postJsonRequest("/signup/email", params)

                .expectStatus()
                .isOk()

                .expectBody()
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.response[0]").isEqualTo(true)
                .jsonPath("$.error").doesNotExist();
    }

    @Test
    @DisplayName("닉네임 중복체크 성공")
    void checkDuplicateNickname_succes_isOk() {
        Map<String, String> params = new HashMap<>();
        params.put("nickname", "seungwooryu");

        clearCookie();
        postJsonRequest("/signup/nickname", params)

                .expectStatus()
                .isOk()

                .expectBody()
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.response[0]").isEqualTo(true)
                .jsonPath("$.error").doesNotExist();
    }

}
