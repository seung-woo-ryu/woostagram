package com.seungwooryu.woostagram.post.controller;

import com.seungwooryu.woostagram.AbstractControllerTests;
import org.junit.jupiter.api.Test;

class PostControllerTest extends AbstractControllerTests {
//    aws key 문제 발생.
//    @Test
//    @Transactional
//    void upload_success() {
//        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
//
//        bodyBuilder.part("imageFile", new ClassPathResource("image/cat_test.png"));
//        bodyBuilder.part("content", "controller #test #content");
//
//        postMultipartRequest("/post", bodyBuilder.build())
//
//                .expectStatus()
//                .isOk()
//
//                .expectBody()
//                .consumeWith(System.out::println)
//                .jsonPath("$.success").isEqualTo(true)
//                .jsonPath("$.error").doesNotExist();
//    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}
