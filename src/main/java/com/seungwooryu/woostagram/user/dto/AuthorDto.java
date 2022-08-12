package com.seungwooryu.woostagram.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.seungwooryu.woostagram.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import static org.springframework.beans.BeanUtils.copyProperties;

@Setter
@Getter
public class AuthorDto {
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("profile_url")
    private String profileUrl;

    private AuthorDto(User source) {
        copyProperties(source, this);
    }

    public static AuthorDto of(User source) {
        return new AuthorDto(source);
    }
}
