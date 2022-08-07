package com.seungwooryu.woostagram.user.service;

import com.seungwooryu.woostagram.user.domain.User;
import com.seungwooryu.woostagram.user.dto.*;
import com.seungwooryu.woostagram.user.exception.DuplicatedArgumentException;
import com.seungwooryu.woostagram.user.exception.UserNotFoundException;
import com.seungwooryu.woostagram.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserDto signup(SignupDto signupDto) {
        checkDuplicate(signupDto);
        return saveUser(signupDto);
    }

    @Transactional
    public UserDto signin(SigninDto signinDto) {
        return findUserByEmailAndPassword(signinDto);
    }

    private UserDto saveUser(SignupDto signupDto) {
        User newUser = User.createUserBySignupDto(signupDto);
        User savedUser = userRepository.save(newUser);

        return new UserDto(savedUser);
    }

    private void checkDuplicate(SignupDto signupDto) {
        if (existsByEmail(signupDto.getEmail())) throw new DuplicatedArgumentException("중복된 이메일입니다");
        if (existsByNickname(signupDto.getNickname())) throw new DuplicatedArgumentException("중복된 닉네임입니다");
    }

    private UserDto findUserByEmailAndPassword(SigninDto signinDto) {
        User loginUser = Optional.ofNullable(userRepository.findByEmailAndPassword(signinDto.getEmail(), signinDto.getPassword()))
                .orElseThrow(() -> new UserNotFoundException());
        return new UserDto(loginUser);
    }

    public boolean checkDuplicationEmail(EmailDto emailDto) {
        return existsByEmail(emailDto.getEmail());
    }

    public boolean checkDuplicationNickname(NicknameDto nicknameDto) {
        return existsByNickname(nicknameDto.getNickname());
    }

    // 외부 클래스에서 사용해서 transactional필요하고
    // 내부 메소드(signin)에서도 transactional로 감싸지는데 어떻게 되는거지?
    @Transactional(readOnly = true)
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException());
    }

    private boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    private boolean existsByNickname(String Nickname) {
        return userRepository.existsByNickname(Nickname);
    }
}

