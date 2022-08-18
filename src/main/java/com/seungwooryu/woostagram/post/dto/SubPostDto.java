package com.seungwooryu.woostagram.post.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.seungwooryu.woostagram.post.domain.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubPostDto {
    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("post_id")
    private Long post_id;

    private SubPostDto(String imageUrl, Long post_id) {
        this.imageUrl = imageUrl;
        this.post_id = post_id;
    }

    public static SubPostDto of(Post post) {
        return new SubPostDto(post.getImageUrl(), post.getId());
    }
}
