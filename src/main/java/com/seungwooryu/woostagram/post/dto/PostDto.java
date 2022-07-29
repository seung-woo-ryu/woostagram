package com.seungwooryu.woostagram.post.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class PostDto {
    private MultipartFile imageFile;
    private String contents;
}
