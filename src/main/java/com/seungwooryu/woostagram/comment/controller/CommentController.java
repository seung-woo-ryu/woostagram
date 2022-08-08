package com.seungwooryu.woostagram.comment.controller;

import com.seungwooryu.woostagram.comment.dto.CommentDto;
import com.seungwooryu.woostagram.comment.facade.CommentFacade;
import com.seungwooryu.woostagram.common.utils.ApiUtils.ApiResult;
import com.seungwooryu.woostagram.user.annotation.LoggedInUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.seungwooryu.woostagram.common.utils.ApiUtils.success;


@RestController
@RequestMapping(value = "/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentFacade commentFacade;

    @PostMapping("/post/{post_id}")
    public ResponseEntity<ApiResult<?>> create(@PathVariable("post_id") Long postId, @Valid @RequestBody CommentDto commentDto, @LoggedInUser String loggedInUserEmail) {
        commentFacade.create(postId, commentDto, loggedInUserEmail);
        return new ResponseEntity<>(success(), HttpStatus.OK);
    }

    @DeleteMapping("/{comment_id}")
    public ResponseEntity<ApiResult<?>> delete(@PathVariable("comment_id") Long commentId, @LoggedInUser String loggedInUserEmail) {
        boolean result = commentFacade.delete(commentId, loggedInUserEmail);
        return new ResponseEntity<>(success(result), HttpStatus.OK);
    }
}
