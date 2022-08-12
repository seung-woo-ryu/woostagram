package com.seungwooryu.woostagram.user.dto;

import com.seungwooryu.woostagram.user.domain.User;
import lombok.*;

import static org.springframework.beans.BeanUtils.copyProperties;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private String nickname;
    private String password;
    private String comment;
    private String profileUrl;

    public UserDto(User source) {
        copyProperties(source, this);
    }
}
