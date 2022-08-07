package com.seungwooryu.woostagram.user.service;

import com.seungwooryu.woostagram.user.domain.User;
import com.seungwooryu.woostagram.user.dto.*;
import com.seungwooryu.woostagram.user.exception.DuplicatedArgumentException;
import com.seungwooryu.woostagram.user.exception.UserNotFoundException;
import com.seungwooryu.woostagram.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;
    private EmailDto emailDto = new EmailDto();
    private NicknameDto nicknameDto = new NicknameDto();
    private SigninDto signinDto = new SigninDto();
    private SignupDto signupDto = new SignupDto();

    private User user;

    private UserDto userDto;

    @BeforeEach
    void setUp() {
        emailDto.setEmail("tmddn645@naver.com");

        nicknameDto.setNickname("tmddn645");

        signinDto.setEmail("tmddn645@naver.com");

        signinDto.setPassword("vvee12");

        signupDto.setEmail("tmddn645@naver.com");
        signupDto.setPassword("vvee12");
        signupDto.setNickname("whatnickname");
        signupDto.setName("seungwoo");

        user = User.of("tmddn645@naver.com", "seungwoo", "whatnickname", "vvee12", "comment", "default img url");

        userDto = new UserDto(user);
    }


    @Test
    @DisplayName("회원가입 실패. 중복된 이메일 에러 발생")
    void signup_duplicateEmail_throwError() {
        when(userRepository.existsByEmail(any(String.class))).thenReturn(true);

        assertThrows(DuplicatedArgumentException.class,
                () -> userService.signup(signupDto));

        verify(userRepository, times(1)).existsByEmail(anyString());
        verify(userRepository, times(0)).existsByNickname(anyString());
    }

    @Test
    @DisplayName("회원가입 실패. 중복된 닉네임 에러 발생")
    void signup_duplicateNickname_throwError() {
        when(userRepository.existsByEmail(any(String.class))).thenReturn(false);
        when(userRepository.existsByNickname(any(String.class))).thenReturn(true);

        InOrder inOrder = inOrder(userRepository);

        assertThrows(DuplicatedArgumentException.class,
                () -> userService.signup(signupDto));

        verify(userRepository, times(1)).existsByEmail(anyString());
        verify(userRepository, times(1)).existsByNickname(anyString());

        inOrder.verify(userRepository).existsByEmail(anyString());
        inOrder.verify(userRepository).existsByNickname(anyString());
    }

    @Test
    @DisplayName("회원가입 성공. ")
    void signup_success_returnUserDto() {
        when(userRepository.existsByEmail(any(String.class))).thenReturn(false);
        when(userRepository.existsByNickname(any(String.class))).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);

        InOrder inOrder = inOrder(userRepository);

        UserDto savedUserDto = userService.signup(signupDto);

        // 동일성 비교하는 lombok추가하면 코드 1줄이지만
        // Dto끼리 동일성 비교할 이유가 없는데 굳이 추가해야되나?
        assertThat(savedUserDto.getPassword()).isEqualTo(userDto.getPassword());
        assertThat(savedUserDto.getEmail()).isEqualTo(userDto.getEmail());
        assertThat(savedUserDto.getNickname()).isEqualTo(userDto.getNickname());
        assertThat(savedUserDto.getName()).isEqualTo(userDto.getName());
        assertThat(savedUserDto.getComment()).isEqualTo(userDto.getComment());
        assertThat(savedUserDto.getProfileUrl()).isEqualTo(userDto.getProfileUrl());

        verify(userRepository, times(1)).existsByEmail(anyString());
        verify(userRepository, times(1)).existsByNickname(anyString());
        verify(userRepository, times(1)).save(any(User.class));

        inOrder.verify(userRepository).existsByEmail(anyString());
        inOrder.verify(userRepository).existsByNickname(anyString());
        inOrder.verify(userRepository).save(any(User.class));
    }

    @Test
    @DisplayName("로그인 성공")
    void signin_success_returnUserDto() {
        when(userRepository.findByEmailAndPassword(anyString(), anyString())).thenReturn(user);

        UserDto loggedinUserDto = userService.signin(signinDto);

        assertThat(loggedinUserDto.getPassword()).isEqualTo(userDto.getPassword());
        assertThat(loggedinUserDto.getEmail()).isEqualTo(userDto.getEmail());
        assertThat(loggedinUserDto.getNickname()).isEqualTo(userDto.getNickname());
        assertThat(loggedinUserDto.getName()).isEqualTo(userDto.getName());
        assertThat(loggedinUserDto.getComment()).isEqualTo(userDto.getComment());
        assertThat(loggedinUserDto.getProfileUrl()).isEqualTo(userDto.getProfileUrl());

        verify(userRepository, times(1)).findByEmailAndPassword(anyString(), anyString());

    }

    @Test
    @DisplayName("로그인 실패.")
    void signin_fail_throwUserNotFoundException() {
        when(userRepository.findByEmailAndPassword(anyString(), anyString())).thenReturn(null);

        assertThrows(UserNotFoundException.class,
                () -> userService.signin(signinDto));

        verify(userRepository, times(1)).findByEmailAndPassword(anyString(), anyString());
    }

    @Test
    @DisplayName("이메일 중복확인. 중복일 경우")
    void checkDuplicationEmail_duplicate_isTrue() {
        when(userRepository.existsByEmail(any(String.class))).thenReturn(true);
        assertThat(userService.checkDuplicationEmail(emailDto)).isTrue();
    }

    @Test
    @DisplayName("이메일 중복확인. 중복아닐 경우")
    void checkDuplicationEmail_duplicate_isFalse() {
        when(userRepository.existsByEmail(any(String.class))).thenReturn(false);
        assertThat(userService.checkDuplicationEmail(emailDto)).isFalse();
    }

    @Test
    @DisplayName("닉네임 중복확인. 중복일 경우")
    void checkDuplicationNickname_duplicate_isTrue() {
        when(userRepository.existsByNickname(any(String.class))).thenReturn(true);
        assertThat(userService.checkDuplicationNickname(nicknameDto)).isTrue();
    }

    @Test
    @DisplayName("닉네임 중복확인. 중복아닐")
    void checkDuplicationNickname_duplicate_isFalse() {
        when(userRepository.existsByNickname(any(String.class))).thenReturn(false);
        assertThat(userService.checkDuplicationNickname(nicknameDto)).isFalse();
    }
}
