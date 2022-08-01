package com.seungwooryu.woostagram.post.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public String upload(MultipartFile file, String folder);

    public void delete(String fileURI);

    String generateFileName(String fileName);
}
