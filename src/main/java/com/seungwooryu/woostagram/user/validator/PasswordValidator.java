package com.seungwooryu.woostagram.user.validator;

import com.seungwooryu.woostagram.user.annotation.Password;
import com.seungwooryu.woostagram.user.dto.SignupDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password,String> {
    @Override
    public void initialize(Password constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        // 영문자, 숫자만
        String pattern = "^[a-zA-Z0-9]+$";

        boolean isValidPassword = Pattern.matches(pattern, password);

        return isValidPassword;
    }
}
