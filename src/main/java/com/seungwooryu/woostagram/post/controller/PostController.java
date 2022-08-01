package com.seungwooryu.woostagram.post.controller;

import com.seungwooryu.woostagram.common.utils.ApiUtils.ApiResult;
import com.seungwooryu.woostagram.post.dto.PostDto;
import com.seungwooryu.woostagram.post.service.PostService;
import com.seungwooryu.woostagram.user.annotation.LoggedInUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.seungwooryu.woostagram.common.utils.ApiUtils.success;

@Slf4j
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResult<?>> upload(@ModelAttribute PostDto postDto, @LoggedInUser String LoggedInUserEmail) {
        Long postId = postService.upload(postDto, LoggedInUserEmail);
        return new ResponseEntity<>(success(List.of(postId)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResult<?>> delete(@PathVariable Long id, @LoggedInUser String LoggedInUserEmail) {
        postService.delete(id, LoggedInUserEmail);
        return new ResponseEntity<>(success(), HttpStatus.OK);
    }

    //ToDo: 수정 페이지 작업 후 진행
    @PutMapping("/{id}")
    public ResponseEntity<ApiResult<?>> update(@ModelAttribute PostDto postDto, @PathVariable Long id, @LoggedInUser String LoggedInUserEmail) {
        Long postId = postService.update(postDto, LoggedInUserEmail, id);
        return new ResponseEntity<>(success(List.of(postId)), HttpStatus.OK);
    }
}
