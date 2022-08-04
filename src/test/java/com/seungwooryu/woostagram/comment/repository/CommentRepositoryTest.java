package com.seungwooryu.woostagram.comment.repository;

import com.seungwooryu.woostagram.comment.domain.Comment;
import com.seungwooryu.woostagram.post.domain.Post;
import com.seungwooryu.woostagram.post.repository.PostRepository;
import com.seungwooryu.woostagram.user.domain.User;
import com.seungwooryu.woostagram.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;


    @Test
    @DisplayName("create 성공")
    @Transactional
    public void createSuccess() {
        //given
        User user = userRepository.findByEmail("tmddn645@naver.com");
        Post post = postRepository.findById(Long.valueOf(1)).get();

        Comment comment = Comment.of("contents", user, post);

        //when
        Comment savedComment = commentRepository.save(comment);

        //then
        assertThat(comment).isEqualTo(savedComment);
        assertThat(comment.getUser()).isEqualTo(user);
        assertThat(comment.getPost()).isEqualTo(post);
    }

    @Test
    @DisplayName("create 실패")
    @Transactional
    public void createFail() {
        //given
        //if user is null
        User user = userRepository.findByEmail("tmddn645");
        Post post = postRepository.findById(Long.valueOf(1)).get();
        Comment comment = Comment.of("contents", user, post);

        //when,then
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            commentRepository.save(comment);
        });
    }

    @Test
    @DisplayName("read 실패")
    @Transactional
    public void readFail() {
        //given
        Optional<Comment> byId = commentRepository.findById(8L);
        //when,then
        Assertions.assertThrows(NoSuchElementException.class,
                () -> {
                    byId.get();
                });
    }
}
