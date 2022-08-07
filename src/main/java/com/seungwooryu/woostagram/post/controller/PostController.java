package com.seungwooryu.woostagram.post.controller;

import com.seungwooryu.woostagram.common.utils.ApiUtils.ApiResult;
import com.seungwooryu.woostagram.post.dto.PostDto;
import com.seungwooryu.woostagram.post.facade.PostFacade;
import com.seungwooryu.woostagram.user.annotation.LoggedInUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.seungwooryu.woostagram.common.utils.ApiUtils.success;

@Slf4j
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostFacade postFacade;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResult<?>> upload(@ModelAttribute PostDto postDto, @LoggedInUser String loggedInUserEmail) {
        Long postId = postFacade.upload(postDto, loggedInUserEmail);
        return new ResponseEntity<>(success(postId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResult<?>> delete(@PathVariable Long id, @LoggedInUser String loggedInUserEmail) {
        Boolean delete = postFacade.delete(id, loggedInUserEmail);
        return new ResponseEntity<>(success(delete), HttpStatus.OK);
    }

    //ToDo: 수정 페이지 작업 후 진행
    @PutMapping("/{id}")
    public ResponseEntity<ApiResult<?>> update(@ModelAttribute PostDto postDto, @PathVariable Long id, @LoggedInUser String loggedInUserEmail) {
        //Long postId = postService.update(postDto, loggedInUserEmail, id);
        return new ResponseEntity<>(success(1), HttpStatus.OK);
    }
}
