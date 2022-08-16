package com.seungwooryu.woostagram.hashtag.service;

import com.seungwooryu.woostagram.hashtag.domain.HashTag;
import com.seungwooryu.woostagram.hashtag.repository.HashTagRepository;
import com.seungwooryu.woostagram.post.domain.Post;
import com.seungwooryu.woostagram.tag.domain.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HashTagService {
    private final HashTagRepository hashTagRepository;

    public void saveHashTags(Post post, List<Tag> tagList) {
        for (Tag tag : tagList) {
            save(post, tag);
        }
    }

    private void save(Post post, Tag tag) {
        hashTagRepository.save(HashTag.of(post, tag));
    }
}
