package com.seungwooryu.woostagram.follow.controller;

import com.seungwooryu.woostagram.AbstractControllerTests;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.seungwooryu.woostagram.common.datainitializer.TestDataInitializer.TestUser2;
import static com.seungwooryu.woostagram.common.datainitializer.TestDataInitializer.TestUser3;

class FollowControllerTest extends AbstractControllerTests {
    @Test
    void create_success() {
        postRequest("/follow/user/" + TestUser3.getId())
                .expectStatus()
                .isOk()

                .expectBody()
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.response").isEmpty()
                .jsonPath("$.error").doesNotExist();
    }

    @Test
    void createAlreadyExistsFollow_success() {
        postRequest("/follow/user/" + TestUser2.getId())
                .expectStatus()
                .isOk()

                .expectBody()
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.response").isEmpty()
                .jsonPath("$.error").doesNotExist();
    }

    @Test
    void delete_success() {
        deleteRequest("/follow/user/" + TestUser2.getId())
                .expectStatus()
                .isOk()

                .expectBody()
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.response").isEqualTo(true)
                .jsonPath("$.error").doesNotExist();
    }

    @Test
    void deleteNotExistsFollow_fail() {
        deleteRequest("/follow/user/" + TestUser3.getId())
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
