package com.seungwooryu.woostagram.tag.service;

import com.seungwooryu.woostagram.tag.domain.Tag;
import com.seungwooryu.woostagram.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {
    private static final Pattern TAG_PATTERN = Pattern.compile("#[A-Za-zㄱ-힣0-9_]{1,10}");
    private final TagRepository tagRepository;

    public List<Tag> upload(String content) {
        List<String> tagStringList = parseContent(content);
        List<Tag> notRegisteredTagList = pickOutNotRegisteredTag(tagStringList);

        return saveTags(notRegisteredTagList);
    }

    private List<String> parseContent(String content) {
        Matcher matcher = TAG_PATTERN.matcher(content);
        List<String> tagStringList = new ArrayList<>();

        while (matcher.find()) {
            tagStringList.add(matcher.group());
        }

        return tagStringList;
    }

    private List<Tag> pickOutNotRegisteredTag(List<String> tagStringList) {
        List<String> registeredTagList = tagRepository.findAllByNameIn(tagStringList)
                .stream()
                .map(tag -> tag.getName())
                .collect(Collectors.toList());

        return tagStringList
                .stream()
                .filter(tagString -> !registeredTagList.contains(tagString))
                .map(Tag::of)
                .collect(Collectors.toList());
    }

    private List<Tag> saveTags(List<Tag> notRegisteredTagList) {
        return notRegisteredTagList
                .stream()
                .map((tag) -> tagRepository.save(tag))
                .collect(Collectors.toList());
    }
}
