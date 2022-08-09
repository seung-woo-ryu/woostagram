package com.seungwooryu.woostagram.user.repository;

import com.seungwooryu.woostagram.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    private final String NEW_USER_EMAIL = "notFound@naver.com";
    private final String NEW_NICKNAME = "newNickname";
    private User user;

    @BeforeEach
    void setUp() {
        user = userRepository.save(User.of("tmddn645@naver.com", "name", "nickname", "vvee12", "default_comment", "default_url"));
    }

    @Test
    @DisplayName("중복된 유저 탐색 by email")
    void existsByEmail() {
        boolean existUser = userRepository.existsByEmail(user.getEmail());
        assertTrue(existUser);
    }

    @Test
    @DisplayName("중복되지 않은 유저 탐색 by email")
    void notExistsByEmail() {
        boolean notExistUser = !userRepository.existsByEmail(NEW_USER_EMAIL);

        assertTrue(notExistUser);
    }

    @Test
    @DisplayName("중복된 유저 탐색 by nickname")
    void existsByNickname() {
        boolean existUser = userRepository.existsByNickname(user.getNickname());
        assertTrue(existUser);
    }

    @Test
    @DisplayName("중복되지 않은 유저 탐색 by nickname")
    void notExistsByNickname() {
        boolean notExistUser = !userRepository.existsByNickname(NEW_NICKNAME);

        assertTrue(notExistUser);
    }
}
