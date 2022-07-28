package com.seungwooryu.woostagram.user.service;

import com.seungwooryu.woostagram.user.domain.User;
import com.seungwooryu.woostagram.user.dto.SigninDto;
import com.seungwooryu.woostagram.user.dto.SignupDto;
import com.seungwooryu.woostagram.user.dto.UserDto;
import com.seungwooryu.woostagram.user.errors.DuplicatedArgumentException;
import com.seungwooryu.woostagram.user.errors.UserNotFoundException;
import com.seungwooryu.woostagram.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto signupUser(SignupDto signupDto) {
        checkDuplicate(signupDto);

        UserDto savedUserDto = saveUser(signupDto);

        return savedUserDto;
    }

    public UserDto signinUser(SigninDto signinDto) {
        UserDto loginUserDto = findUserExist(signinDto);

        return loginUserDto;
    }

    @Transactional
    public UserDto saveUser(SignupDto signupDto) {
        User newUser = User.createUserBySignupDto(signupDto);
        User savedUser = userRepository.save(newUser);

        UserDto savedUserDto = new UserDto(savedUser);

        return savedUserDto;
    }

    @Transactional(readOnly = true)
    public void checkDuplicate(SignupDto signupDto) {
        List<FieldError> fieldErrors = new ArrayList<>();

        final String email = signupDto.getEmail();
        final String nickname = signupDto.getNickname();

        //이메일 중복
        if (userRepository.existsByEmail(email)) {
            log.info("중복된 이메일");
            fieldErrors.add(new FieldError("signupDto", "email", "중복된 이메일입니다"));
        }
        //닉네임 중복
        if (userRepository.existsByNickname(nickname)) {
            log.info("중복된 닉네임");
            fieldErrors.add(new FieldError("signupDto", "nickname", "중복된 닉네임입니다"));
        }
        // 중복된 속성 존재시 exception 발생
        if (!fieldErrors.isEmpty()) {
            log.info("UserService.signupUser, DuplicatedArgumentException Error");
            throw new DuplicatedArgumentException(fieldErrors, HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional(readOnly = true)
    public UserDto findUserExist(SigninDto signinDto) {
        List<FieldError> fieldErrors = new ArrayList<>();

        final String email = signinDto.getEmail();
        final String password = signinDto.getPassword();

        User loginUser = userRepository.findByEmail(email);

        Optional.ofNullable(loginUser).ifPresentOrElse(
                (user) -> {
                    final String userPassword = user.getPassword();

                    if (!userPassword.equals(password)) {
                        log.info("틀린 패스워드");
                        fieldErrors.add(new FieldError("signinDto", "password", "비밀번호가 일치하지 않습니다"));
                    }
                },

                () -> {
                    log.info("존재하지 않는 이메일");
                    fieldErrors.add(new FieldError("signinDto", "email", "존재하지 않는 이메일입니다"));
                });

        if (!fieldErrors.isEmpty()) {
            log.info("UserService.signinUser, UserNotFoundException Error");
            throw new UserNotFoundException(fieldErrors, HttpStatus.BAD_REQUEST);
        }

        UserDto loginUserDto = new UserDto(loginUser);

        return loginUserDto;
    }
}
