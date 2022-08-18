package com.seungwooryu.woostagram.like.controller;

import com.seungwooryu.woostagram.AbstractControllerTests;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.seungwooryu.woostagram.common.datainitializer.TestDataInitializer.testLike1ByUser1AndPost1;
import static com.seungwooryu.woostagram.common.datainitializer.TestDataInitializer.testPost1ByUser1;

class LikeControllerTest extends AbstractControllerTests {
    private final String WRONG_POST_ID = "404";
    private final String WRONG_LIKE_ID = "404";

    @Test
    void create_success_isOk() {
        postRequest("/like/post/" + testPost1ByUser1.getId())
                .expectStatus()
                .isOk()

                .expectBody()
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.response").isEmpty()
                .jsonPath("$.error").doesNotExist();
    }

    @Test
    void create_fail_throwPostNotFoundException() {
        postRequest("/like/post/" + WRONG_POST_ID)
                .expectStatus()
                .isBadRequest()

                .expectBody()
                .jsonPath("$.success").isEqualTo(false)
                .jsonPath("$.response").isEmpty()
                .jsonPath("$.error.message").isEqualTo(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .jsonPath("$.error.status").isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void delete_success_isOk() {
        deleteRequest("/like/post/" + testLike1ByUser1AndPost1.getId())
                .expectStatus()
                .isOk()

                .expectBody()
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.response.[0]").isEqualTo(true)
                .jsonPath("$.error").doesNotExist();
    }

    @Test
    void delete_fail_throwPostNotFoundException() {
        deleteRequest("/like/post/" + WRONG_POST_ID)
                .expectStatus()
                .isBadRequest()

                .expectBody()
                .jsonPath("$.success").isEqualTo(false)
                .jsonPath("$.response").isEmpty()
                .jsonPath("$.error.message").isEqualTo(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .jsonPath("$.error.status").isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void delete_fail_throwLikeNotExistsException() {
        deleteRequest("/like/post/" + WRONG_LIKE_ID)
                .expectStatus()
                .isBadRequest()

                .expectBody()
                .jsonPath("$.success").isEqualTo(false)
                .jsonPath("$.response").isEmpty()
                .jsonPath("$.error.message").isEqualTo(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .jsonPath("$.error.status").isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}
