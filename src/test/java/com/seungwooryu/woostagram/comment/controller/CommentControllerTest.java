package com.seungwooryu.woostagram.comment.controller;

import com.seungwooryu.woostagram.AbstractControllerTests;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

class CommentControllerTest extends AbstractControllerTests {

    @Test
    @Transactional
    void create_success() {
        Map<String, String> params = new HashMap<>();
        params.put("contents", "댓글 내용 작성. 최대 200글자까지");

        postJsonRequest("/comment/post/1", params)

                .expectStatus()
                .isOk()

                .expectBody()
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.response").isEmpty()
                .jsonPath("$.error").doesNotExist();
    }

    @Test
    @Transactional
    void delete_success_returnTrue() {
        deleteRequest("/comment/1")

                .expectStatus()
                .isOk()

                .expectBody()
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.response.[0]").isEqualTo(true)
                .jsonPath("$.error").doesNotExist();
    }
}
