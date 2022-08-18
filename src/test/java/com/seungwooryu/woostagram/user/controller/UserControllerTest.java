package com.seungwooryu.woostagram.user.controller;

import com.seungwooryu.woostagram.AbstractControllerTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static com.seungwooryu.woostagram.common.datainitializer.TestConstants.*;
import static com.seungwooryu.woostagram.common.datainitializer.TestDataInitializer.testUser1;

class UserControllerTest extends AbstractControllerTests {
    @Test
    @DisplayName("로그인 성공.")
    void signin_success_isOk() {
        Map<String, String> params = new HashMap<>();
        params.put("email", testUser1.getEmail());
        params.put("password", testUser1.getPassword());

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
        params.put("email", UNCORRECT_EMAIL);
        params.put("password", DEFAULT_PASSWORD);

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
        params.put("email", NEW_EMAIL);
        params.put("nickname", NEW_NICKNAME);
        params.put("password", DEFAULT_PASSWORD);
        params.put("name", DEFAULT_NAME);

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
        params.put("email", testUser1.getEmail());
        params.put("nickname", NEW_NICKNAME);
        params.put("password", DEFAULT_PASSWORD);
        params.put("name", DEFAULT_NAME);

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
        params.put("email", testUser1.getEmail());

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
        params.put("nickname", testUser1.getNickname());

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
