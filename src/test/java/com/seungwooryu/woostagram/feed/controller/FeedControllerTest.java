package com.seungwooryu.woostagram.feed.controller;

import com.seungwooryu.woostagram.AbstractControllerTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Collections;

class FeedControllerTest extends AbstractControllerTests {
    @Test
    @DisplayName("get feed 성공.")
    void get_success() {
        Long LastFeedId = 6L;
        Long size = 2L;

        getRequest("/feed/post/" + LastFeedId.toString() + "?size=" + size.toString())
                .expectStatus()
                .isOk()

                .expectBody()
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.response.size()").isEqualTo(size)
                .jsonPath("$.error").doesNotExist();
    }

    @Test
    @DisplayName("get feed 성공했지만 가져올 게시글이 없는 경우")
    void get_success_returnResponseEmpty() {
        Long LastFeedId = 1L;
        Long size = 2L;

        getRequest("/feed/post/" + LastFeedId.toString() + "?size=" + size.toString())
                .expectStatus()
                .isOk()

                .expectBody()
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.response.size()").isEqualTo(Collections.EMPTY_LIST.size())
                .jsonPath("$.error").doesNotExist();
    }

    @Test
    @DisplayName("@RequestMapping 타입 불일치 에러")
    void get_fail_throwRuntimeException() {
        getRequest("/feed/post/abc")
                .expectStatus()
                .isBadRequest()

                .expectBody()
                .jsonPath("$.success").isEqualTo(false)
                .jsonPath("$.response.size()").isEqualTo(Collections.EMPTY_LIST.size())
                .jsonPath("$.error.status").isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("@PathVariable 타입 불일치 에러")
    void get_fail_throwRuntimeException2() {
        getRequest("/feed/post/7?size=abcd")
                .expectStatus()
                .isBadRequest()

                .expectBody()
                .jsonPath("$.success").isEqualTo(false)
                .jsonPath("$.response.size()").isEqualTo(Collections.EMPTY_LIST.size())
                .jsonPath("$.error.status").isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}
