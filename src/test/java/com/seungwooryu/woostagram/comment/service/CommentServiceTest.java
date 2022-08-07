package com.seungwooryu.woostagram.comment.service;

import com.seungwooryu.woostagram.comment.domain.Comment;
import com.seungwooryu.woostagram.comment.repository.CommentRepository;
import com.seungwooryu.woostagram.post.domain.Post;
import com.seungwooryu.woostagram.post.exception.PostNotFoundException;
import com.seungwooryu.woostagram.user.domain.User;
import com.seungwooryu.woostagram.user.exception.AuthenticationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {
    @Mock
    private CommentRepository commentRepository;
    @InjectMocks
    private CommentService commentService;
    private User user = User.of("tmddn64@naver.com", "seungwoo", "ohzsh", "vvee12", "comment", "imageUrl");
    ;
    private Post post = Post.of("contents", "image url", user);
    ;
    @Spy
    private Comment comment = Comment.of("contents", user, post);
    ;

    @Test
    void delete_success_returnTrue() {
        doReturn(Optional.ofNullable(comment)).when(commentRepository).findById(any(Long.class));
        doReturn(true).when(comment).isAuthor(any(User.class));
        doNothing().when(commentRepository).delete(comment);

        assertThat(commentService.delete(1L, user)).isTrue();
    }

    @Test
    void delete_fail_throwPostNotFoundException() {
        doReturn(Optional.ofNullable(null)).when(commentRepository).findById(any(Long.class));

        assertThrows(PostNotFoundException.class,
                () -> commentService.delete(1L, user));
    }

    @Test
    void delete_fail_throwAuthenticationException() {
        doReturn(Optional.ofNullable(comment)).when(commentRepository).findById(any(Long.class));
        doReturn(false).when(comment).isAuthor(any(User.class));

        assertThrows(AuthenticationException.class,
                () -> commentService.delete(1L, user));
    }


}
