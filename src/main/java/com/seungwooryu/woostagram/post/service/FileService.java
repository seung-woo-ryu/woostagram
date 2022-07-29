package com.seungwooryu.woostagram.post.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public String upload(MultipartFile file);

    public Boolean delete(String fileURI);
}
