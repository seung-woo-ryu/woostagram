package com.seungwooryu.woostagram.post.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.seungwooryu.woostagram.post.domain.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDto {
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("content")
    private String content;

    private ArticleDto(String imageUrl, String content) {
        this.imageUrl = imageUrl;
        this.content = content;
    }

    public static ArticleDto of(Post post) {
        return new ArticleDto(post.getImageUrl(), post.getContents());
    }
}
