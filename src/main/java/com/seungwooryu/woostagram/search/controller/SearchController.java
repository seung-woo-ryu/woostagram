package com.seungwooryu.woostagram.search.controller;

import com.seungwooryu.woostagram.common.utils.ApiUtils.ApiResult;
import com.seungwooryu.woostagram.search.dto.SearchDto;
import com.seungwooryu.woostagram.search.facade.SearchFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.seungwooryu.woostagram.common.utils.ApiUtils.success;

@RestController
@RequiredArgsConstructor
public class SearchController {
    private final SearchFacade searchFacade;

    @GetMapping("/search")
    public ResponseEntity<ApiResult<?>> get(@RequestParam(required = false, defaultValue = "") String word) {
        SearchDto searchDto = searchFacade.get(word);
        return new ResponseEntity<>(success(searchDto), HttpStatus.OK);
    }
}
