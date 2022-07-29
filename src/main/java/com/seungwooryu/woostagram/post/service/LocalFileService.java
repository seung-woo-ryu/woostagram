package com.seungwooryu.woostagram.post.service;

import org.springframework.web.multipart.MultipartFile;

public class LocalFileService implements FileService {
    private static final String FILE_ = "위치 어디로";

    @Override
    public String upload(MultipartFile file) {
        return null;
    }

    @Override
    public Boolean delete(String fileURI) {
        return null;
    }
}
