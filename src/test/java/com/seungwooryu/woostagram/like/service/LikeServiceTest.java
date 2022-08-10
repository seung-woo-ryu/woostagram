package com.seungwooryu.woostagram.like.service;

import com.seungwooryu.woostagram.like.domain.Like;
import com.seungwooryu.woostagram.like.exception.LikeNotExistsException;
import com.seungwooryu.woostagram.like.repository.LikeRepository;
import com.seungwooryu.woostagram.post.domain.Post;
import com.seungwooryu.woostagram.post.service.PostService;
import com.seungwooryu.woostagram.user.domain.User;
import com.seungwooryu.woostagram.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LikeServiceTest {
    @InjectMocks
    LikeService likeService;
    @Mock
    UserService userService;
    @Mock
    PostService postService;
    @Mock
    LikeRepository likeRepository;
    @Spy
    User user = User.of("tmddn645@naver.com",
            "seungwoo",
            "nickname",
            "vvee12",
            "contents",
            "profileURL");
    @Spy
    Post post = Post.of("contents",
            "imageUrl",
            user);
    @Spy
    Like like = Like.of(user, post);
    ;

    @Test
    void create_success() {
        doReturn(false).when(likeRepository).existsByUser_IdAndPost_Id(anyLong(), anyLong());
        doReturn(1L).when(user).getId();
        doReturn(1L).when(post).getId();

        assertDoesNotThrow(() -> likeService.create(user, post));
    }

    @Test
    void create_fail() {
        doReturn(true).when(likeRepository).existsByUser_IdAndPost_Id(anyLong(), anyLong());
        doReturn(1L).when(user).getId();
        doReturn(1L).when(post).getId();

        assertDoesNotThrow(() -> likeService.create(user, post));

        verify(likeRepository, times(1)).existsByUser_IdAndPost_Id(anyLong(), anyLong());
        verify(likeRepository, times(0)).save(any(Like.class));
    }

    @Test
    void delete_success() {
        doReturn(1L).when(user).getId();
        doReturn(1L).when(post).getId();
        doReturn(Optional.ofNullable(like)).when(likeRepository).findByUserIdAndPostId(anyLong(), anyLong());
        doNothing().when(likeRepository).delete(like);

        assertThat(likeService.delete(user, post)).isTrue();
    }

    @Test
    void delete_fail_throwLikeNotExistsException() {
        doReturn(1L).when(user).getId();
        doReturn(1L).when(post).getId();
        doReturn(Optional.ofNullable(null)).when(likeRepository).findByUserIdAndPostId(anyLong(), anyLong());

        assertThrows(LikeNotExistsException.class,
                () -> likeService.delete(user, post));
        
        verify(likeRepository, times(0)).delete(any(Like.class));
    }
}



