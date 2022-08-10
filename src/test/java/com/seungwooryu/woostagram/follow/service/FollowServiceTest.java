package com.seungwooryu.woostagram.follow.service;

import com.seungwooryu.woostagram.follow.domain.Follow;
import com.seungwooryu.woostagram.follow.exception.FollowNotFoundException;
import com.seungwooryu.woostagram.follow.repository.FollowRepository;
import com.seungwooryu.woostagram.user.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.seungwooryu.woostagram.common.datainitializer.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FollowServiceTest {

    @Mock
    FollowRepository followRepository;

    @Spy
    User fromUser = User.of("fromUser@naver.com", DEFAULT_NAME, "fromUser", DEFAULT_PASSWORD, DEFAULT_COMMENT, DEFAULT_URL);
    @Spy
    User toUser = User.of("toUser@naver.com", DEFAULT_NAME, "toUser", DEFAULT_PASSWORD, DEFAULT_COMMENT, DEFAULT_URL);
    Follow follow = Follow.of(fromUser, toUser);
    @InjectMocks
    FollowService followService;

    @Test
    void create_success_void() {
        doReturn(false).when(followRepository).existsByFromUser_IdAndToUser_Id(anyLong(), anyLong());
        doReturn(1L).when(fromUser).getId();
        doReturn(2L).when(toUser).getId();
        doReturn(follow).when(followRepository).save(any(Follow.class));

        assertDoesNotThrow(() ->
                followService.create(fromUser, toUser));
    }

    @Test
    void create_fail_void() {
        doReturn(true).when(followRepository).existsByFromUser_IdAndToUser_Id(anyLong(), anyLong());
        doReturn(1L).when(fromUser).getId();
        doReturn(2L).when(toUser).getId();

        followService.create(fromUser, toUser);

        verify(followRepository, times(0)).save(any(Follow.class));
    }

    @Test
    void delete_success_returnTrue() {
        doReturn(Optional.ofNullable(follow)).when(followRepository).findByFromUser_IdAndToUser_Id(anyLong(), anyLong());
        doReturn(1L).when(fromUser).getId();
        doReturn(2L).when(toUser).getId();
        doNothing().when(followRepository).delete(any(Follow.class));

        assertThat(followService.delete(fromUser, toUser)).isTrue();
    }

    @Test
    void delete_fail_throwFollowNotFoundException() {
        doReturn(Optional.ofNullable(null)).when(followRepository).findByFromUser_IdAndToUser_Id(anyLong(), anyLong());
        doReturn(1L).when(fromUser).getId();
        doReturn(2L).when(toUser).getId();

        assertThrows(FollowNotFoundException.class,
                () -> followService.delete(fromUser, toUser));

        verify(followRepository, times(0)).delete(any(Follow.class));
    }
}
