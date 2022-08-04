package com.seungwooryu.woostagram.comment.service;

import com.seungwooryu.woostagram.comment.domain.Comment;
import com.seungwooryu.woostagram.comment.repository.CommentRepository;
import com.seungwooryu.woostagram.post.domain.Post;
import com.seungwooryu.woostagram.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @InjectMocks
    CommentService commentService;
    @Mock
    CommentRepository commentRepository;


    @Spy
    private User user = User.of("tmddn645@naver.com", "유승우", "tmddn645", "zxcvasf12", "nothing", "default img url");
    ;

    @Spy
    private Post post = Post.of("nothing", "defaultImageUrl", user);
    ;

    @Spy
    private Comment comment = Comment.of("nothing", user, post);

    @BeforeEach
    void setUp() {

    }

    @Test
    void delete() {
        System.out.println(comment);
        doReturn(comment).when(commentService).findById(anyLong());
        doReturn(Optional.ofNullable(comment)).when(commentRepository).findById(1L);
        doReturn(Long.valueOf(1)).when(user).getId();
        doReturn(Long.valueOf(1)).when(comment).getId();
        doReturn(true).when(post).isAuthor(any(User.class));

        assertThat(commentService.delete(1L, user)).isEqualTo(true);
    }

    @Test
    void create() {


    }


    @Test
    void findById() {
    }
}
