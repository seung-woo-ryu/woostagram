package com.seungwooryu.woostagram.like.controller;

import com.seungwooryu.woostagram.common.utils.ApiUtils.ApiResult;
import com.seungwooryu.woostagram.like.facade.LikeFacade;
import com.seungwooryu.woostagram.user.annotation.LoggedInUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.seungwooryu.woostagram.common.utils.ApiUtils.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {
    private final LikeFacade likeFacade;

    @PostMapping("/post/{post_id}")
    public ResponseEntity<ApiResult<?>> create(@PathVariable("post_id") Long postId, @LoggedInUser String loggedInUserEmail) {
        likeFacade.create(loggedInUserEmail, postId);
        return new ResponseEntity<>(success(), HttpStatus.OK);
    }

    @DeleteMapping("/post/{post_id}")
    public ResponseEntity<ApiResult<?>> delete(@PathVariable("post_id") Long postId, @LoggedInUser String loggedInUserEmail) {
        boolean result = likeFacade.delete(loggedInUserEmail, postId);
        return new ResponseEntity<>(success(result), HttpStatus.OK);
    }

}
