package com.seungwooryu.woostagram.user.dto;

import com.seungwooryu.woostagram.user.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.seungwooryu.woostagram.common.datainitializer.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class AuthorDtoTest {

    @Test
    void copyTest() {
        User user = User.of(NEW_EMAIL, DEFAULT_NAME, NEW_NICKNAME, DEFAULT_PASSWORD, DEFAULT_COMMENT, DEFAULT_URL);
        AuthorDto authorDto = AuthorDto.of(user);

        assertThat(authorDto.getNickname()).isEqualTo(user.getNickname());
        assertThat(authorDto.getProfileUrl()).isEqualTo(user.getProfileUrl());
    }
}
