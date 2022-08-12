package com.seungwooryu.woostagram.comment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.seungwooryu.woostagram.comment.domain.Comment;
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
    @JsonProperty("comment_id")
    private Long commentId;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 200, message = "최대 200글자까지 입니다")
    private String contents;

    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("profile_url")
    private String profileUrl;

    private CommentDto(Long commentId, String contents, String nickname, String profile_url) {
        this.commentId = commentId;
        this.contents = contents;
        this.nickname = nickname;
        this.profileUrl = profile_url;
    }

    public static CommentDto of(Comment comment) {
        return new CommentDto(comment.getId(), comment.getContents(), comment.getUser().getNickname(), comment.getUser().getProfileUrl());
    }
}
