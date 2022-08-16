package com.seungwooryu.woostagram.tag.repository;

import com.seungwooryu.woostagram.post.domain.Post;
import com.seungwooryu.woostagram.post.repository.PostRepository;
import com.seungwooryu.woostagram.tag.domain.Tag;
import com.seungwooryu.woostagram.user.domain.User;
import com.seungwooryu.woostagram.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static com.seungwooryu.woostagram.common.datainitializer.TestConstants.*;

@DataJpaTest
class TagRepositoryTest {
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    private User user;
    private Post post;
    private Tag tag1;
    private Tag tag2;
    private Tag tag3;

    @BeforeEach
    void setUp() {
        user = userRepository.save(User.of("user1@naver.com", DEFAULT_NAME, "user1", DEFAULT_PASSWORD, DEFAULT_CONTENTS, DEFAULT_URL));
        post = postRepository.save(Post.of(DEFAULT_CONTENTS, DEFAULT_URL, user));
        tag1 = tagRepository.save(Tag.of(DEFAULT_HASHTAG));
        tag2 = tagRepository.save(Tag.of("#hashtag2"));
        tag3 = tagRepository.save(Tag.of("#hashtag3"));
    }

    @Test
    void findAllByNameIn() {
        List<String> tagStringList = Arrays.asList("#hashtag", "#hashtag4");

        List<Tag> allByNameIn = tagRepository.findAllByNameIn(tagStringList);
        System.out.println(allByNameIn);
    }
}
