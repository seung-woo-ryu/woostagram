package com.seungwooryu.woostagram.post.repository;

import com.seungwooryu.woostagram.post.domain.Post;
import com.seungwooryu.woostagram.user.domain.User;
import com.seungwooryu.woostagram.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.seungwooryu.woostagram.common.datainitializer.TestConstants.*;

@DataJpaTest
class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    private User user1;
    private User user2;
    private User user3;

    private Post post1ByUser1;
    private Post post2ByUser1;
    private Post post3ByUser1;

    private Post post1ByUser2;
    private Post post2ByUser2;

    private Post post1ByUser3;
    private Post post2ByUser3;

    @BeforeEach
    void setUp() {
        user1 = userRepository.save(User.of("user1@naver.com", DEFAULT_NAME, "user1", DEFAULT_PASSWORD, DEFAULT_CONTENTS, DEFAULT_URL));
        user2 = userRepository.save(User.of("user2@naver.com", DEFAULT_NAME, "user2", DEFAULT_PASSWORD, DEFAULT_CONTENTS, DEFAULT_URL));
        user3 = userRepository.save(User.of("user3@naver.com", DEFAULT_NAME, "user3", DEFAULT_PASSWORD, DEFAULT_CONTENTS, DEFAULT_URL));

        post1ByUser1 = postRepository.save(Post.of(DEFAULT_CONTENTS, DEFAULT_URL, user1));
        post2ByUser1 = postRepository.save(Post.of(DEFAULT_CONTENTS, DEFAULT_URL, user1));
        post3ByUser1 = postRepository.save(Post.of(DEFAULT_CONTENTS, DEFAULT_URL, user1));

        post1ByUser2 = postRepository.save(Post.of(DEFAULT_CONTENTS, DEFAULT_URL, user2));
        post2ByUser2 = postRepository.save(Post.of(DEFAULT_CONTENTS, DEFAULT_URL, user2));

        post1ByUser3 = postRepository.save(Post.of(DEFAULT_CONTENTS, DEFAULT_URL, user3));
        post2ByUser3 = postRepository.save(Post.of(DEFAULT_CONTENTS, DEFAULT_URL, user3));
    }

    @Test
    void findAllByAuthorIdIn() {
        //given
        /*List<Long> targetIdList = Arrays.asList(user3.getId(), user2.getId());
        List<Long> userIdList = new ArrayList<>(targetIdList);
        long target = 0;

        //when
        for (Long userId : targetIdList) {
            target += postRepository.findAllByAuthorIdIn(Arrays.asList(userId), 1L).size();
        }
        List<Post> result = postRepository.findAllByAuthorIdIn(userIdList, 100L);

        //then
        assertThat(result.size()).isEqualTo(targetIdList.size());*/
    }
}
