package com.seungwooryu.woostagram.post.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class PostDto {
    @JsonProperty("image_file")
    private MultipartFile imageFile;

    @NotNull
    private String content;
}
