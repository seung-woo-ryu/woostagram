package com.seungwooryu.woostagram.comment.repository;

import com.seungwooryu.woostagram.comment.domain.Comment;
import com.seungwooryu.woostagram.post.domain.Post;
import com.seungwooryu.woostagram.post.repository.PostRepository;
import com.seungwooryu.woostagram.user.domain.User;
import com.seungwooryu.woostagram.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static com.seungwooryu.woostagram.common.datainitializer.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;

    private User user1;
    private User user2;
    private User user3;

    private Post post1ByUser1;
    private Post post2ByUser2;

    private Comment comment1ByUser1AndPost1;
    private Comment comment2ByUser2AndPost1;
    private Comment comment3ByUser3AndPost1;
    private Comment comment1ByUser3AndPost2;


    @BeforeEach
    void setUp() {
        user1 = userRepository.save(User.of("user1@naver.com", DEFAULT_NAME, "user1", DEFAULT_PASSWORD, DEFAULT_CONTENTS, DEFAULT_URL));
        user2 = userRepository.save(User.of("user2@naver.com", DEFAULT_NAME, "user2", DEFAULT_PASSWORD, DEFAULT_CONTENTS, DEFAULT_URL));
        user3 = userRepository.save(User.of("user3@naver.com", DEFAULT_NAME, "user3", DEFAULT_PASSWORD, DEFAULT_CONTENTS, DEFAULT_URL));

        post1ByUser1 = postRepository.save(Post.of(DEFAULT_CONTENTS, DEFAULT_URL, user1));
        post2ByUser2 = postRepository.save(Post.of(DEFAULT_CONTENTS, DEFAULT_URL, user2));

        comment1ByUser1AndPost1 = commentRepository.save(Comment.of(DEFAULT_CONTENTS, user1, post1ByUser1));
        comment2ByUser2AndPost1 = commentRepository.save(Comment.of(DEFAULT_CONTENTS, user2, post1ByUser1));
        comment3ByUser3AndPost1 = commentRepository.save(Comment.of(DEFAULT_CONTENTS, user3, post1ByUser1));
        comment1ByUser3AndPost2 = commentRepository.save(Comment.of(DEFAULT_CONTENTS, user3, post2ByUser2));
    }

    @Test
    void findAllByPostId() {
        Long postId = post1ByUser1.getId();

        List<Comment> result = commentRepository.findAllByPostId(postId);

        assertThat(result.size()).isEqualTo(3);
    }
}
