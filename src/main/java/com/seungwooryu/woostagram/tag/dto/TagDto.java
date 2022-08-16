package com.seungwooryu.woostagram.tag.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagDto {
    private String name;

    private TagDto(String name) {
        this.name = name;
    }

    public static TagDto of(String name) {
        return new TagDto(name);
    }
}
