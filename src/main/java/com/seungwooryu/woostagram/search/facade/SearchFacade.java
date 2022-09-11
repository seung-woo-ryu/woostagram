package com.seungwooryu.woostagram.search.facade;

import com.seungwooryu.woostagram.hashtag.service.HashTagService;
import com.seungwooryu.woostagram.post.dto.SubPostDto;
import com.seungwooryu.woostagram.search.dto.SearchDto;
import com.seungwooryu.woostagram.tag.service.TagService;
import com.seungwooryu.woostagram.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SearchFacade {
    private final UserService userService;
    private final TagService tagService;
    private final HashTagService hashTagService;

    public SearchDto get(String word) {
        String wildcardWord = (word.equals("")) ?
                word :
                "%" + word + "%";
        return SearchDto.of(userService.findAllByNicknameLike(wildcardWord),
                tagService.findAllByNameLike(wildcardWord));
    }

    public List<SubPostDto> getHashtag(String hashtag) {
        String wildcardWord = (hashtag.equals("")) ?
                hashtag :
                "%" + hashtag + "%";

        return hashTagService.findAllByTag_NameLike(wildcardWord);
    }
}
