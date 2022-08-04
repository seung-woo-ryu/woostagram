package com.seungwooryu.woostagram.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString

public class NicknameDto {
    @NotNull(message = "닉네임 Null 금지입니다")
    @NotBlank(message = "닉네임 공백 금지입니다")
    @Size(min = 2, max = 16, message = "최소 2자리 이상 최대 16자리 이하입니다")
    private String nickname;
}
