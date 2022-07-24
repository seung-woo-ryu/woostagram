package com.seungwooryu.woostagram.user.annotation;

import com.seungwooryu.woostagram.user.validator.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "영문자와 숫자만 가능합니다";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
