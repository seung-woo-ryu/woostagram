package com.seungwooryu.woostagram.comment.dto;

import com.seungwooryu.woostagram.post.domain.Post;
import com.seungwooryu.woostagram.user.domain.User;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class CommentDto {
    @NotNull
    @NotBlank
    @Size(min = 1, max = 200, message = "최대 200글자까지 입니다")
    private String contents;
    private User user;
    private Post post;
}
