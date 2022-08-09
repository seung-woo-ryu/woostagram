package com.seungwooryu.woostagram.user.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.seungwooryu.woostagram.common.datainitializer.TestConstants.NEW_EMAIL;
import static com.seungwooryu.woostagram.common.datainitializer.TestConstants.NEW_NICKNAME;
import static com.seungwooryu.woostagram.common.datainitializer.TestDataInitializer.TestUser1;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("중복된 유저 탐색 by email")
    void existsByEmail() {
        boolean existUser = userRepository.existsByEmail(TestUser1.getEmail());

        assertTrue(existUser);
    }

    @Test
    @DisplayName("중복되지 않은 유저 탐색 by email")
    void notExistsByEmail() {
        boolean notExistUser = !userRepository.existsByEmail(NEW_EMAIL);

        assertTrue(notExistUser);
    }

    @Test
    @DisplayName("중복된 유저 탐색 by nickname")
    void existsByNickname() {
        boolean existUser = userRepository.existsByNickname(TestUser1.getNickname());
        assertTrue(existUser);
    }

    @Test
    @DisplayName("중복되지 않은 유저 탐색 by nickname")
    void notExistsByNickname() {
        boolean notExistUser = !userRepository.existsByNickname(NEW_NICKNAME);

        assertTrue(notExistUser);
    }
}
