package com.seungwooryu.woostagram.search.controller;

import com.seungwooryu.woostagram.AbstractControllerTests;
import org.junit.jupiter.api.Test;

class SearchControllerTest extends AbstractControllerTests {

    @Test
    void get() {
        String word = "content";

        getRequest("/search?word=" + word)
                .expectStatus()
                .isOk()

                .expectBody()
                .consumeWith(System.out::println)

                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.response[0].nicknames").hasJsonPath()
                .jsonPath("$.response[0].nicknames").isArray()
                .jsonPath("$.response[0].nicknames").isEmpty()
                .jsonPath("$.response[0].tags").hasJsonPath()
                .jsonPath("$.response[0].tags").isArray()
                .jsonPath("$.error").doesNotExist();
    }
}
