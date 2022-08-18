package com.seungwooryu.woostagram.mypage.controller;

import com.seungwooryu.woostagram.AbstractControllerTests;
import org.junit.jupiter.api.Test;

import static com.seungwooryu.woostagram.common.datainitializer.TestDataInitializer.testUser2;

class MyPageControllerTest extends AbstractControllerTests {

    @Test
    void get() {
        String nickname = testUser2.getNickname();

        getRequest("/page?nickname=" + nickname)
                .expectStatus()
                .isOk()

                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.response").hasJsonPath()
                .jsonPath("$.response[0].nickname").hasJsonPath()
                .jsonPath("$.response[0].profile_url").hasJsonPath()
                .jsonPath("$.response[0].profile_content").hasJsonPath()
                .jsonPath("$.response[0].posts").hasJsonPath()
                .jsonPath("$.response[0].is_author").hasJsonPath()
                .jsonPath("$.response[0].number_of_posts").hasJsonPath()
                .jsonPath("$.response[0].number_of_followers").hasJsonPath()
                .jsonPath("$.response[0].number_of_followings").hasJsonPath()
                .jsonPath("$.error").doesNotExist();
    }
}
