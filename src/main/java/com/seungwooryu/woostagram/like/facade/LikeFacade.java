package com.seungwooryu.woostagram.like.facade;

import com.seungwooryu.woostagram.like.service.LikeService;
import com.seungwooryu.woostagram.post.service.PostService;
import com.seungwooryu.woostagram.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeFacade {
    private final UserService userService;
    private final LikeService likeService;
    private final PostService postService;

    public void create(String loggedInUserEmail, Long postId) {
        likeService.create(userService.findUserByEmail(loggedInUserEmail),
                postService.findPostById(postId));
    }

    public boolean delete(String loggedInUserEmail, Long postId) {
        return likeService.delete(userService.findUserByEmail(loggedInUserEmail),
                postService.findPostById(postId));
    }
}
