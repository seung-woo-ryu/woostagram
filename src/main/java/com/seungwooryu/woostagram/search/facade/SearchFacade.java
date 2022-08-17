package com.seungwooryu.woostagram.search.facade;

import com.seungwooryu.woostagram.search.dto.SearchDto;
import com.seungwooryu.woostagram.tag.service.TagService;
import com.seungwooryu.woostagram.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchFacade {
    private final UserService userService;
    private final TagService tagService;

    public SearchDto get(String word) {
        String wildcardWord = (word.equals("")) ?
                word :
                "%" + word + "%";
        return SearchDto.of(userService.findAllByNicknameLike(wildcardWord),
                tagService.findAllByNameLike(wildcardWord));
    }
}
