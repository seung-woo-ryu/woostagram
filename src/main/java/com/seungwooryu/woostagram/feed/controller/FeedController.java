package com.seungwooryu.woostagram.feed.controller;

import com.seungwooryu.woostagram.common.utils.ApiUtils.ApiResult;
import com.seungwooryu.woostagram.feed.dto.FeedDto;
import com.seungwooryu.woostagram.feed.facade.FeedFacade;
import com.seungwooryu.woostagram.user.annotation.LoggedInUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.seungwooryu.woostagram.common.utils.ApiUtils.success;
import static org.springframework.data.domain.Pageable.ofSize;

@RestController
@RequiredArgsConstructor
public class FeedController {
    private final FeedFacade feedFacade;

    @GetMapping({"/feed/post/{post_id}", "/feed"})
    public ResponseEntity<ApiResult<?>> get(@LoggedInUser String loggedInUserEmail, @PathVariable(required = false, name = "post_id") Long postId, @RequestParam(required = false, name = "size", defaultValue = "2") Integer size) {
        List<FeedDto> FeedDtoList = feedFacade.get(loggedInUserEmail, postId, ofSize(size));
        return new ResponseEntity<>(success(FeedDtoList), HttpStatus.OK);

    }
}
