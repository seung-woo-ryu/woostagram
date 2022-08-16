package com.seungwooryu.woostagram.tag.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {
    //ToDo: mock 정확한 원리. 안에 메소드들 다 구현안되어있을텐데 어떻게 돌아가지?
    @Mock
    TagService tagService;

    @Test
    void parseContent() {
        String content = "#Content1 #content2#content3 asl;dkfjk" +
                "" +
                "asdlfk;jasl;dkfjas;lkdf" +
                "#13\\n" +
                "123aaasdfasdf";
        List<String> answerList = Arrays.asList("#Content1", "#content2", "#content3", "#13");

        List<String> tagList = ReflectionTestUtils.invokeMethod(tagService, "parseContent", content);

        assertThat(tagList).hasSize(answerList.size())
                .containsAll(answerList);
    }
}
