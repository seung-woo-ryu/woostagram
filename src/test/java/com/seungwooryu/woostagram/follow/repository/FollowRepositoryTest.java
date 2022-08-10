package com.seungwooryu.woostagram.follow.repository;

import com.seungwooryu.woostagram.follow.domain.Follow;
import com.seungwooryu.woostagram.follow.exception.FollowNotFoundException;
import com.seungwooryu.woostagram.user.domain.User;
import com.seungwooryu.woostagram.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class FollowRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    FollowRepository followRepository;

    private User fromUser;
    private User toUser;
    private Follow follow;

    @BeforeEach
    void setUp() {
        fromUser = userRepository.save(User.of("from@naver.com", "fromUser", "from", "vvee12", "default_comment", "default_url"));
        toUser = userRepository.save(User.of("to@naver.com", "toUser", "to", "vvee12", "default_comment", "default_url"));
        follow = followRepository.save(Follow.of(fromUser, toUser));
    }

    @Test
    void existsByFromIdAndToId_success() {
        Long fromUserId = fromUser.getId();
        Long toUserId = toUser.getId();

        boolean result = followRepository.existsByFromUser_IdAndToUser_Id(fromUserId, toUserId);

        assertThat(result).isTrue();
    }

    @Test
    void existsByFromIdAndToId_fail() {
        Long fromUserId = fromUser.getId();
        Long wrong_toUserId = 404L;

        boolean result = followRepository.existsByFromUser_IdAndToUser_Id(fromUserId, wrong_toUserId);

        assertThat(result).isFalse();
    }

    @Test
    void findByFromIdAndToId_success() {
        Long fromUserId = fromUser.getId();
        Long toUserId = toUser.getId();

        assertDoesNotThrow(() ->
                followRepository.findByFromUser_IdAndToUser_Id(fromUserId, toUserId)
                        .orElseThrow(FollowNotFoundException::new));
    }

    @Test
    void findByFromIdAndToId_fail() {
        Long fromUserId = fromUser.getId();
        Long wrong_toUserId = 404L;

        assertThrows(FollowNotFoundException.class,
                () -> followRepository.findByFromUser_IdAndToUser_Id(fromUserId, wrong_toUserId)
                        .orElseThrow(FollowNotFoundException::new));
    }
}
