package com.seungwooryu.woostagram.search.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchDto {
    private List<String> nicknames;
    private List<String> tags;

    private SearchDto(List<String> nicknames, List<String> tags) {
        this.nicknames = nicknames;
        this.tags = tags;
    }

    public static SearchDto of(List<String> nicknames, List<String> names) {
        return new SearchDto(nicknames, names);
    }
}
