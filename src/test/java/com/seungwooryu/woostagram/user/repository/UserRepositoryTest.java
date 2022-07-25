package com.seungwooryu.woostagram.user.repository;

import com.seungwooryu.woostagram.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

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
        if (existUser) {
            User getUser = userRepository.findByEmail(email);
            System.out.println(getUser);
        }

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
        if (notExistUser) {
            User getUser = userRepository.findByEmail(email);
            System.out.println(getUser);
        }

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
        if (existUser) {
            User getUser = userRepository.findByNickname(nickname);
            System.out.println(getUser);
        }

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
        if (notExistUser) {
            User getUser = userRepository.findByNickname(nickname);
            System.out.println(getUser);
        }

        //then
        assertTrue(notExistUser);
    }
}