package com.seungwooryu.woostagram.comment.facade;

import com.seungwooryu.woostagram.comment.dto.CommentDto;
import com.seungwooryu.woostagram.comment.service.CommentService;
import com.seungwooryu.woostagram.post.domain.Post;
import com.seungwooryu.woostagram.post.service.PostService;
import com.seungwooryu.woostagram.user.domain.User;
import com.seungwooryu.woostagram.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentFacade {
    private final UserService userService;
    private final CommentService commentService;
    private final PostService postService;

    public void create(Long postId, CommentDto commentDto, String loggedInUserEmail) {
        User sessionUser = userService.findUserByEmail(loggedInUserEmail);
        Post postWithComment = postService.findPostById(postId);

        commentService.create(sessionUser, postWithComment, commentDto.getContents());
    }

    public boolean delete(Long commentId, String loggedInUserEmail) {
        return commentService.delete(commentId, userService.findUserByEmail(loggedInUserEmail));
    }
}
