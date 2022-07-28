package com.seungwooryu.woostagram.user.dto;

import com.seungwooryu.woostagram.user.annotation.Password;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SigninDto {
    @Email(message = "이메일 양식이여야 합니다.")
    @NotNull(message = "이메일 Null 금지")
    @NotBlank(message = "이메일 공백 금지")
    private String email;

    @Size(min = 2, max = 16, message = "최소 2자리 이상 최대 16자리 이하")
    @Password
    private String password;
}
