package com.seungwooryu.woostagram.post.controller;

import com.seungwooryu.woostagram.common.utils.ApiUtils.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.seungwooryu.woostagram.common.utils.ApiUtils.success;

@Slf4j
@RestController
@RequestMapping("/post")
public class PostController {
    @PostMapping
    public ResponseEntity<ApiResult<?>> upload(@RequestPart(value = "image_file", required = false) MultipartFile imageFile, @RequestParam String content) {
        System.out.println(imageFile.getOriginalFilename());
        System.out.println(content);

        return new ResponseEntity<>(success(null), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResult<?>> delete(@PathVariable int id) {

        return new ResponseEntity<>(success(null), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResult<?>> update(MultipartFile file, String content, @PathVariable int id) {

        return new ResponseEntity<>(success(null), HttpStatus.OK);
    }

}
