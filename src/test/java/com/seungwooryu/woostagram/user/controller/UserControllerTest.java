package com.seungwooryu.woostagram.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seungwooryu.woostagram.user.dto.SignupDto;
import com.seungwooryu.woostagram.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입 성공. 유효한 정보들")
    void signupSuccess() throws Exception {
        //given
        SignupDto request = new SignupDto("tmddn645@nave.rcom", "seungwoo", "seung-woo-ryu", "asdf123");
        //when,then
        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/signup")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.error").isEmpty());
    }

    @Test
    @DisplayName("회원가입 실패. 유효하지 않은 정보들")
    void signupFail() throws Exception {
        // 어떤 가입 정보들이 유효하지 않은지 body에 json형태로 반환.
        // 유효하지 않은 정보만 json에 추가. 유효성 검증한 속성은 추가 X
        // ex: {"email": "유효하지 않은 양식입니다", "nickname":"닉네임이 중복입니다","name": "최소 2글자 이상 최대 16글자 이하입니다."}

        // given
        SignupDto request = new SignupDto("tmddn645", "1", "asdfasdf", "sksnnr12");

        mockMvc.perform(post("/signup")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.response").isEmpty())
                .andExpect(jsonPath("$.error.message").value(HttpStatus.BAD_REQUEST.getReasonPhrase()))
                .andExpect(jsonPath("$.error.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.error.errors.length()").value(2));
    }
}
