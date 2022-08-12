package com.seungwooryu.woostagram.like.repository;

import com.seungwooryu.woostagram.like.domain.Like;
import com.seungwooryu.woostagram.post.domain.Post;
import com.seungwooryu.woostagram.post.repository.PostRepository;
import com.seungwooryu.woostagram.user.domain.User;
import com.seungwooryu.woostagram.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class LikeRepositoryTest {
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    private final Long WRONG_POST_ID = 404L;
    private User user;
    private User user2;
    private User user3;
    private Post post;
    private Like like;

    @BeforeEach
    void setUp() {
        user = userRepository.save(User.of("tmddn645@naver.com", "name", "nickname", "vvee12", "default_comment", "default_url"));
        user2 = userRepository.save(User.of("tmddn6451@naver.com", "name", "nickname2", "vvee12", "default_comment", "default_url"));
        user3 = userRepository.save(User.of("tmddn6452@naver.com", "name", "nickname3", "vvee12", "default_comment", "default_url"));

        post = postRepository.save(Post.of("contents", "url", user));

        like = likeRepository.save(Like.of(user, post));
        like = likeRepository.save(Like.of(user2, post));
        like = likeRepository.save(Like.of(user3, post));
    }

    @Test
    void existsByAuthorIdAndPostId_success_returnTrue() {
        //given
        Long userId = user.getId();
        Long postId = post.getId();
        //when
        boolean result = likeRepository.existsByUser_IdAndPost_Id(userId, postId);
        //then
        assertThat(result).isTrue();
    }

    @Test
    void existsByAuthorIdAndPostId_success_returnFalse() {
        //given
        Long userId = user.getId();
        Long postId = WRONG_POST_ID;
        //when
        boolean result = likeRepository.existsByUser_IdAndPost_Id(userId, postId);
        //then
        assertThat(result).isFalse();
    }

    @Test
    void countByPost_id() {
        Long postId = post.getId();

        Long likeCount = likeRepository.countByPost_id(postId);

        assertThat(likeCount).isEqualTo(3L);
    }
}
