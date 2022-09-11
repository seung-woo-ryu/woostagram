package com.seungwooryu.woostagram.hashtag.repository;

import com.seungwooryu.woostagram.hashtag.domain.HashTag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HashTagRepositoryTest {
    @Autowired
    private HashTagRepository hashTagRepository;

    @Test
    @Transactional
    void findAllByTag_NameLike() {
        String hashtag = "%#post%";

        List<HashTag> hashTagList = hashTagRepository.findAllByTag_NameLike(hashtag);

        assertThat(hashTagList).hasSize(3);
    }
}
