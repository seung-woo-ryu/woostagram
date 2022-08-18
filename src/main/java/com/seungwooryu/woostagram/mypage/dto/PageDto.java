package com.seungwooryu.woostagram.mypage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.seungwooryu.woostagram.post.dto.SubPostDto;
import com.seungwooryu.woostagram.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageDto {
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("profile_url")
    private String profileUrl;
    @JsonProperty("profile_content")
    private String content;

    @JsonProperty("posts")
    private List<SubPostDto> subPostDtoList;

    @JsonProperty("is_author")
    private Boolean isAuthor;

    @JsonProperty("number_of_posts")
    private Long postNum;
    @JsonProperty("number_of_followers")
    private Long followerNum;
    @JsonProperty("number_of_followings")
    private Long followingNum;

    private PageDto(String nickname, String profileUrl, String content, List<SubPostDto> subPostDtoList, Boolean isAuthor, Long postNum, Long followerNum, Long followingNum) {
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.content = content;
        this.subPostDtoList = subPostDtoList;
        this.isAuthor = isAuthor;
        this.postNum = postNum;
        this.followerNum = followerNum;
        this.followingNum = followingNum;
    }

    public static PageDto of(User user, List<SubPostDto> subPostDtoList, Boolean isAuthor, Long postNum, Long followerNum, Long followingNum) {
        return new PageDto(user.getNickname(), user.getProfileUrl(), user.getComment(), subPostDtoList, isAuthor, postNum, followerNum, followingNum);
    }
}
