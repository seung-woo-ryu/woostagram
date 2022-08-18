package com.seungwooryu.woostagram.mypage.controller;

import com.seungwooryu.woostagram.common.utils.ApiUtils.ApiResult;
import com.seungwooryu.woostagram.mypage.dto.PageDto;
import com.seungwooryu.woostagram.mypage.facade.MyPageFacade;
import com.seungwooryu.woostagram.user.annotation.LoggedInUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.seungwooryu.woostagram.common.utils.ApiUtils.success;

@RestController
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageFacade myPageFacade;

    @GetMapping("/page")
    public ResponseEntity<ApiResult<PageDto>> get(@LoggedInUser String loggedInUserEmail, @RequestParam(required = true) String nickname) {
        PageDto pageDto = myPageFacade.get(loggedInUserEmail, nickname);
        return new ResponseEntity<>(success(pageDto), HttpStatus.OK);
    }
}
