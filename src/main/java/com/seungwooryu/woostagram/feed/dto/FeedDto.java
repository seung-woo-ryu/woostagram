package com.seungwooryu.woostagram.feed.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.seungwooryu.woostagram.comment.dto.CommentDto;
import com.seungwooryu.woostagram.post.domain.Post;
import com.seungwooryu.woostagram.post.dto.ArticleDto;
import com.seungwooryu.woostagram.user.dto.AuthorDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FeedDto {
    @JsonProperty("feed_id")
    private Long postId;
    @JsonProperty("author")
    private AuthorDto authorDto;
    @JsonProperty("article")
    private ArticleDto articleDto;
    @JsonProperty("like_count")
    private Long likeCount;
    @JsonProperty("comment_list")
    List<CommentDto> commentList;

    private FeedDto(List<CommentDto> commentDtoListOrderByCreatedAtDesc, Long likeCount, Post post) {
        this.postId = post.getId();
        this.commentList = commentDtoListOrderByCreatedAtDesc;
        this.likeCount = likeCount;
        this.authorDto = AuthorDto.of(post.getUser());
        this.articleDto = ArticleDto.of(post);
    }

    public static FeedDto of(List<CommentDto> commentDtoListOrderByCreatedAtDesc, Long likeCount, Post post) {
        return new FeedDto(commentDtoListOrderByCreatedAtDesc, likeCount, post);
    }
}
