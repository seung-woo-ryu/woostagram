package com.seungwooryu.woostagram.like.controller;

import com.seungwooryu.woostagram.AbstractControllerTests;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class LikeControllerTest extends AbstractControllerTests {
    @Test
    void create_success_isOk() {
        postRequest("/like/post/1")
                .expectStatus()
                .isOk()

                .expectBody()
                .consumeWith(System.out::println)

                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.response").isEmpty()
                .jsonPath("$.error").doesNotExist();
    }

    @Test
    void create_fail_throwPostNotFoundException() {
        postRequest("/like/post/4")
                .expectStatus()
                .isBadRequest()

                .expectBody()
                .consumeWith(System.out::println)

                .jsonPath("$.success").isEqualTo(false)
                .jsonPath("$.response").isEmpty()
                .jsonPath("$.error.message").isEqualTo(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .jsonPath("$.error.status").isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void delete_success_isOk() {
        deleteRequest("/like/post/1")
                .expectStatus()
                .isOk()

                .expectBody()
                .consumeWith(System.out::println)

                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.response.[0]").isEqualTo(true)
                .jsonPath("$.error").doesNotExist();
    }

    @Test
    void delete_fail_throwPostNotFoundException() {
        deleteRequest("/like/post/4")
                .expectStatus()
                .isBadRequest()

                .expectBody()
                .consumeWith(System.out::println)

                .jsonPath("$.success").isEqualTo(false)
                .jsonPath("$.response").isEmpty()
                .jsonPath("$.error.message").isEqualTo(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .jsonPath("$.error.status").isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void delete_fail_throwLikeNotExistsException() {
        deleteRequest("/like/post/2")
                .expectStatus()
                .isBadRequest()

                .expectBody()
                .consumeWith(System.out::println)

                .jsonPath("$.success").isEqualTo(false)
                .jsonPath("$.response").isEmpty()
                .jsonPath("$.error.message").isEqualTo(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .jsonPath("$.error.status").isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}
