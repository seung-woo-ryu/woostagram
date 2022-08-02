package com.seungwooryu.woostagram.post.facade;

import com.seungwooryu.woostagram.post.dto.PostDto;
import com.seungwooryu.woostagram.post.service.FileService;
import com.seungwooryu.woostagram.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostFacade {
    private static final String FOLDER_NAME = "post";
    private final PostService postService;
    private final FileService fileService;

    public Boolean delete(Long id, String email) {
        String imageUrl = postService.delete(id, email);
        fileService.delete(imageUrl);
        return true;
    }

    public Long upload(PostDto postDto, String userEmail) {
        final String savedImagePath = fileService.upload(postDto.getImageFile(), FOLDER_NAME);
        return postService.upload(postDto, userEmail, savedImagePath);
    }
}
