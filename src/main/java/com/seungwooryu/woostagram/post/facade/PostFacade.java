package com.seungwooryu.woostagram.post.facade;

import com.seungwooryu.woostagram.post.dto.PostDto;
import com.seungwooryu.woostagram.post.service.FileService;
import com.seungwooryu.woostagram.post.service.PostService;
import com.seungwooryu.woostagram.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostFacade {
    private static final String FOLDER_NAME = "post";
    private final PostService postService;
    private final FileService fileService;
    private final UserService userService;

    public Boolean delete(Long id, String loggedInUserEmail) {
        String imageUrl = postService.delete(id, userService.findUserByEmail(loggedInUserEmail));
        fileService.delete(imageUrl);
        return true;
    }

    public Long upload(PostDto postDto, String loggedInUserEmail) {
        final String savedImagePath = fileService.upload(postDto.getImageFile(), FOLDER_NAME);
        return postService.upload(postDto, userService.findUserByEmail(loggedInUserEmail), savedImagePath);
    }
}
