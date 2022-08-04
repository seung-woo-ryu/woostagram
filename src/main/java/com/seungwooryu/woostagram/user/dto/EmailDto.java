package com.seungwooryu.woostagram.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class EmailDto {
    @Email(message = "이메일 양식이여야 합니다.")
    @NotNull(message = "이메일 Null 금지입니다")
    @NotBlank(message = "이메일 공백 금지입니다")
    private String email;
}
