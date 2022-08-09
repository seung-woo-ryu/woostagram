package com.seungwooryu.woostagram.comment.controller;

import com.seungwooryu.woostagram.AbstractControllerTests;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.seungwooryu.woostagram.common.datainitializer.TestConstants.DEFAULT_CONTENTS;
import static com.seungwooryu.woostagram.common.datainitializer.TestDataInitializer.TestComment1ByUser1AndPost1;
import static com.seungwooryu.woostagram.common.datainitializer.TestDataInitializer.TestPost1ByUser1;

class CommentControllerTest extends AbstractControllerTests {

    @Test
    void create_success() {
        Map<String, String> params = new HashMap<>();
        params.put("contents", DEFAULT_CONTENTS);

        postJsonRequest("/comment/post/" + TestPost1ByUser1.getId(), params)

                .expectStatus()
                .isOk()

                .expectBody()
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.response").isEmpty()
                .jsonPath("$.error").doesNotExist();
    }

    @Test
    void delete_success_returnTrue() {
        deleteRequest("/comment/" + TestComment1ByUser1AndPost1.getId())

                .expectStatus()
                .isOk()

                .expectBody()
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.response.[0]").isEqualTo(true)
                .jsonPath("$.error").doesNotExist();
    }
}
