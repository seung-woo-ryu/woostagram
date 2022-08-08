package com.seungwooryu.woostagram.user.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("중복된 유저 탐색 by email")
    void existsByEmail() {
        //given
        String email = "tmddn645@naver.com";

        //when
        boolean existUser = userRepository.existsByEmail(email);

        //then
        assertTrue(existUser);
    }

    @Test
    @DisplayName("중복되지 않은 유저 탐색 by email")
    void notExistsByEmail() {
        //given
        String email = "1231123@naver.com";

        //when
        boolean notExistUser = !userRepository.existsByEmail(email);

        //then
        assertTrue(notExistUser);
    }

    @Test
    @DisplayName("중복된 유저 탐색 by nickname")
    void existsByNickname() {
        //given
        String nickname = "seungwooryu";

        //when
        boolean existUser = userRepository.existsByNickname(nickname);

        //then
        assertTrue(existUser);
    }

    @Test
    @DisplayName("중복되지 않은 유저 탐색 by nickname")
    void notExistsByNickname() {
        //given
        String nickname = "seungwooryu123";

        //when
        boolean notExistUser = !userRepository.existsByNickname(nickname);

        //then
        assertTrue(notExistUser);
    }
}
