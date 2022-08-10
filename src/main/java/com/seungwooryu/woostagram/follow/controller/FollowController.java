package com.seungwooryu.woostagram.follow.controller;

import com.seungwooryu.woostagram.common.utils.ApiUtils.ApiResult;
import com.seungwooryu.woostagram.follow.facade.FollowFacade;
import com.seungwooryu.woostagram.user.annotation.LoggedInUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.seungwooryu.woostagram.common.utils.ApiUtils.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follow")
public class FollowController {
    private final FollowFacade followFacade;

    @PostMapping("/user/{user_id}")
    public ResponseEntity<ApiResult<?>> create(@LoggedInUser String loggedInUserEmail, @PathVariable("user_id") Long followedUserId) {
        followFacade.create(loggedInUserEmail, followedUserId);
        return new ResponseEntity<>(success(), HttpStatus.OK);
    }

    @DeleteMapping("/user/{user_id}")
    public ResponseEntity<ApiResult<?>> delete(@LoggedInUser String loggedInUserEmail, @PathVariable("user_id") Long followedUserId) {
        boolean result = followFacade.delete(loggedInUserEmail, followedUserId);
        return new ResponseEntity<>(success(result), HttpStatus.OK);
    }
}
