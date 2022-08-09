package com.seungwooryu.woostagram;


import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@AutoConfigureWebTestClient
@TestPropertySource("classpath:application-dev.yml")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AbstractControllerTests {

    @LocalServerPort
    private int port;
    @Autowired
    WebTestClient webTestClient;
    private String cookie;
    private static final String TEST_EMAIL = "tmddn645@naver.com";
    private static final String TEST_PASSWORD = "qwerasdf";

    @BeforeEach
    protected void setup() {
        loginRequest(TEST_EMAIL, TEST_PASSWORD);
    }

    protected void loginRequest(String testEmail, String testPassword) {
        Map<String, String> params = new HashMap<>();
        params.put("email", testEmail);
        params.put("password", testPassword);

        this.cookie = postJsonRequest("/signin", params)
                .expectBody()
                .returnResult()
                .getResponseHeaders()
                .getFirst(HttpHeaders.SET_COOKIE);
    }

    protected ResponseSpec postJsonRequest(String uri, Map<String, String> params) {
        return webTestClient.post()
                .uri(uri)
                .header(HttpHeaders.COOKIE, cookie)
                .body(Mono.just(params), Map.class)
                .exchange();
    }

    protected ResponseSpec postRequest(String uri) {
        return webTestClient.post()
                .uri(uri)
                .header(HttpHeaders.COOKIE, cookie)
                .exchange();
    }

    protected ResponseSpec deleteRequest(String uri) {
        return webTestClient.delete()
                .uri(uri)
                .header(HttpHeaders.COOKIE, cookie)
                .exchange();
    }

    protected void clearCookie() {
        this.cookie = "";
    }
}
