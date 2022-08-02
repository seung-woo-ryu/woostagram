package com.seungwooryu.woostagram.user.service;

import com.seungwooryu.woostagram.user.domain.User;
import com.seungwooryu.woostagram.user.dto.SigninDto;
import com.seungwooryu.woostagram.user.dto.SignupDto;
import com.seungwooryu.woostagram.user.dto.UserDto;
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
        if (!userRepository.existsByEmail(signupDto.getEmail())) throw new DuplicatedArgumentException("중복된 이메일입니다");
        if (!userRepository.existsByNickname(signupDto.getNickname()))
            throw new DuplicatedArgumentException("중복된 닉네임입니다");
    }

    private UserDto findUserByEmailAndPassword(SigninDto signinDto) {
        User loginUser = Optional.ofNullable(userRepository.findByEmailAndPassword(signinDto.getEmail(), signinDto.getPassword()))
                .orElseThrow(() -> new UserNotFoundException());
        return new UserDto(loginUser);
    }
}
