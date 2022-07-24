package com.seungwooryu.woostagram.user.service;

import com.seungwooryu.woostagram.user.domain.User;
import com.seungwooryu.woostagram.user.dto.SignupDto;
import com.seungwooryu.woostagram.user.errors.DuplicatedArgumentException;
import com.seungwooryu.woostagram.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {
    UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly=true)
    public boolean checkEmailDuplicate(String email) {return userRepository.existsByEmail(email);}
    @Transactional(readOnly=true)
    public boolean checkNicknameDuplicate(String nickname) {return userRepository.existsByNickname(nickname);}

    @Transactional
    public User saveUser(SignupDto signupDto) {
        User newUser = User.createUserBySignupDto(signupDto);
        return userRepository.save(newUser);
    }

    public User signupUser(SignupDto signupDto) {
        List<FieldError> fieldErrors = new ArrayList<>();
        //이메일 중복
        if (checkEmailDuplicate(signupDto.getEmail())) {
            fieldErrors.add(new FieldError("signupDto","email","중복된 이메일입니다"));
        }
        //닉네임 중복
        if (checkNicknameDuplicate(signupDto.getNickname())) {
            fieldErrors.add(new FieldError("signupDto","nickname","중복된 닉네임입니다"));
        }

        // 중복된 속성 존재시 exception 발생
        if (! fieldErrors.isEmpty()) {
            log.info("Duplicated Errors");
            throw new DuplicatedArgumentException(fieldErrors);
        }

        // 중복되지 않은 유저라면 saveUser
        User savedUser = saveUser(signupDto);

        return savedUser;
    }
}
