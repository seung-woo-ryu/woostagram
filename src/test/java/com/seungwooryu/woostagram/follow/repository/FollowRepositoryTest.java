package com.seungwooryu.woostagram.follow.repository;

import com.seungwooryu.woostagram.follow.domain.Follow;
import com.seungwooryu.woostagram.follow.exception.FollowNotFoundException;
import com.seungwooryu.woostagram.user.domain.User;
import com.seungwooryu.woostagram.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

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
    private User toUser2;
    private User toUser3;
    private Follow follow;

    @BeforeEach
    void setUp() {
        fromUser = userRepository.save(User.of("from@naver.com", "fromUser", "from", "vvee12", "default_comment", "default_url"));
        toUser = userRepository.save(User.of("to@naver.com", "toUser", "to", "vvee12", "default_comment", "default_url"));
        toUser2 = userRepository.save(User.of("to2@naver.com", "toUser", "to2", "vvee12", "default_comment", "default_url"));
        toUser3 = userRepository.save(User.of("to3@naver.com", "toUser", "to3", "vvee12", "default_comment", "default_url"));

        follow = followRepository.save(Follow.of(fromUser, toUser));
        follow = followRepository.save(Follow.of(fromUser, toUser2));
        follow = followRepository.save(Follow.of(fromUser, toUser3));
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

    @Test
    void findAllByFromUser_Id() {
        Long fromUserId = fromUser.getId();

        List<Follow> followList = followRepository.findAllByFromUser_Id(fromUserId);

        assertThat(followList.size()).isEqualTo(3);
        assertThat(followList.get(0).getToUser()).isEqualTo(toUser);
        assertThat(followList.get(1).getToUser()).isEqualTo(toUser2);
        assertThat(followList.get(2).getToUser()).isEqualTo(toUser3);
    }
}
