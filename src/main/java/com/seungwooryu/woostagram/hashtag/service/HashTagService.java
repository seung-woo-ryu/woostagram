package com.seungwooryu.woostagram.hashtag.service;

import com.seungwooryu.woostagram.hashtag.domain.HashTag;
import com.seungwooryu.woostagram.hashtag.repository.HashTagRepository;
import com.seungwooryu.woostagram.post.domain.Post;
import com.seungwooryu.woostagram.post.dto.SubPostDto;
import com.seungwooryu.woostagram.tag.domain.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HashTagService {
    private final HashTagRepository hashTagRepository;

    @Transactional
    public void saveHashTags(Post post, List<Tag> tagList) {
        for (Tag tag : tagList) {
            save(post, tag);
        }
    }

    private void save(Post post, Tag tag) {
        hashTagRepository.save(HashTag.of(post, tag));
    }

    @Transactional(readOnly = true)
    public List<SubPostDto> findAllByTag_NameLike(String wildcardWord) {
        return hashTagRepository.findAllByTag_NameLike(wildcardWord)
                .stream()
                .map(hashTag -> SubPostDto.of(hashTag.getPost()))
                .collect(Collectors.toList());
    }
}
