package com.seungwooryu.woostagram.post.service;

import com.seungwooryu.woostagram.post.domain.Post;
import com.seungwooryu.woostagram.post.dto.PostDto;
import com.seungwooryu.woostagram.post.exception.PostNotFoundException;
import com.seungwooryu.woostagram.post.repository.PostRepository;
import com.seungwooryu.woostagram.user.domain.User;
import com.seungwooryu.woostagram.user.dto.UserDto;
import com.seungwooryu.woostagram.user.exception.AuthenticationException;
import com.seungwooryu.woostagram.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {
    @Spy
    private User user = user = User.of("tmddn64@naver.com", "seungwoo", "ohzsh", "vvee12", "comment", "imageUrl");
    ;
    private UserDto userDto;

    @Spy
    private Post post = Post.of("contents", "image url", user);
    ;
    private PostDto postDto;
    @Mock
    private MultipartFile multipartFile;

    @BeforeEach
    void setUp() {
        userDto = new UserDto(user);
        postDto = new PostDto();
        postDto.setContent("dafault");
        postDto.setImageFile(multipartFile);
    }

    @Mock
    PostRepository postRepository;
    @Mock
    UserRepository userRepository;
    @InjectMocks
    PostService postService;

    @Test
    @DisplayName("파일 업로드 성공.")
    void upload_success_returnPostId() {
        doReturn(user).when(userRepository).findByEmail(anyString());
        doReturn(1L).when(post).getId();
        doReturn(post).when(postRepository).save(any(Post.class));

        assertThat(postService.upload(postDto, any(User.class), "any path"))
                .isEqualTo(1L);
    }

    @Test
    @DisplayName("파일 삭제 성공")
    void delete_success_returnImageUrl() {
        doReturn(Optional.ofNullable(post)).when(postRepository).findById(any(Long.class));
        doReturn(user).when(userRepository).findByEmail(anyString());
        doReturn(true).when(post).isAuthor(any(User.class));
        doNothing().when(postRepository).deleteById(any(Long.class));

        assertThat(postService.delete(1L, any(User.class)))
                .isEqualTo(post.getImageUrl());
    }

    @Test
    @DisplayName("파일 삭제 실패. 유저가 존재하지 않는 포스트를 삭제할 경우")
    void delete_fail_throwPostNotFoundException() {
        doThrow(PostNotFoundException.class).when(postRepository).findById(any(Long.class));
        assertThrows(PostNotFoundException.class,
                () -> postService.delete(1L, any(User.class)));
    }

    @Test
    @DisplayName("파일 삭제 실패. 유저가 다른 사람의 포스트를 삭제할 경우")
    void delete_fail_throwAuthenticationException() {
        doReturn(Optional.ofNullable(post)).when(postRepository).findById(any(Long.class));
        doReturn(user).when(userRepository).findByEmail(anyString());
        doReturn(false).when(post).isAuthor(any(User.class));

        assertThrows(AuthenticationException.class,
                () -> postService.delete(1L, any(User.class)));
    }

    @Test
    void update() {
    }
}
