package com.seungwooryu.woostagram.like.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class LikeRepositoryTest {
    @Autowired
    LikeRepository likeRepository;

    @Test
    void existsByAuthorIdAndPostId_success_returnTrue() {
        //given
        Long userId = 1L;
        Long postId = 1L;
        //when
        boolean result = likeRepository.existsByUser_IdAndPost_Id(userId, postId);
        //then
        assertThat(result).isTrue();
    }

    @Test
    void existsByAuthorIdAndPostId_success_returnFalse() {
        //given
        Long userId = 1L;
        Long postId = 2L;
        //when
        boolean result = likeRepository.existsByUser_IdAndPost_Id(userId, postId);
        //then
        assertThat(result).isFalse();
    }
}
