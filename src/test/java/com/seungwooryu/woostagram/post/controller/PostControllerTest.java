package com.seungwooryu.woostagram.post.controller;

import com.seungwooryu.woostagram.AbstractControllerTests;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.transaction.annotation.Transactional;

class PostControllerTest extends AbstractControllerTests {
    @Test
    @Transactional
    void upload_success() {
        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();

        bodyBuilder.part("imageFile", new ClassPathResource("image/cat_test.png"));
        bodyBuilder.part("content", "controller #test #content");

        postMultipartRequest("/post", bodyBuilder.build())

                .expectStatus()
                .isOk()

                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.error").doesNotExist();
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}
