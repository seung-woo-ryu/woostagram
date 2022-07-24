package com.seungwooryu.woostagram.user.dto;

import com.seungwooryu.woostagram.user.domain.User;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    private String email;
    private String name;
    private String nickname;
    private String password;
    private String comment;
    private String profileUrl;

    public UserDto(User source) {
        BeanUtils.copyProperties(source, this);
    }
}
