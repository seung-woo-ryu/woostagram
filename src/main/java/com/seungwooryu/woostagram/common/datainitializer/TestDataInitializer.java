package com.seungwooryu.woostagram.common.datainitializer;

import com.seungwooryu.woostagram.comment.domain.Comment;
import com.seungwooryu.woostagram.comment.repository.CommentRepository;
import com.seungwooryu.woostagram.like.domain.Like;
import com.seungwooryu.woostagram.like.repository.LikeRepository;
import com.seungwooryu.woostagram.post.domain.Post;
import com.seungwooryu.woostagram.post.repository.PostRepository;
import com.seungwooryu.woostagram.user.domain.User;
import com.seungwooryu.woostagram.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static com.seungwooryu.woostagram.common.datainitializer.TestConstants.*;

@Profile("dev")
@Component
@RequiredArgsConstructor
public class TestDataInitializer implements ApplicationRunner {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    public static User TestUser1;
    public static User TestUser2;
    public static User TestUser3;
    public static Post TestPost1ByUser1;
    public static Post TestPost2ByUser1;
    public static Post TestPost3ByUser1;
    public static Post TestPost1ByUser2;
    public static Post TestPost2ByUser2;
    public static Post TestPost3ByUser2;
    public static Comment TestComment1ByUser1AndPost1;
    public static Comment TestComment2ByUser2AndPost1;
    public static Comment TestComment1ByUser3AndPost2;
    public static Like TestLike1ByUser1AndPost1;
    public static Like TestLike2ByUser2AndPost1;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        TestUser1 = saveUser("tmddn645@naver.com", "seungwoo", "loginUser");
        TestUser2 = saveUser("catLove1@naver.com", "kanghunmo", "catmeow");
        TestUser3 = saveUser("dogLove1@naver.com", "kimhunmo", "dogwalwal");

        TestPost1ByUser1 = savePost(TestUser1);
        TestPost2ByUser1 = savePost(TestUser1);
        TestPost3ByUser1 = savePost(TestUser1);

        TestPost1ByUser2 = savePost(TestUser2);
        TestPost2ByUser2 = savePost(TestUser2);
        TestPost3ByUser2 = savePost(TestUser2);

        TestComment1ByUser1AndPost1 = saveComment(TestUser1, TestPost1ByUser1);
        TestComment2ByUser2AndPost1 = saveComment(TestUser2, TestPost1ByUser1);

        TestComment1ByUser3AndPost2 = saveComment(TestUser3, TestPost2ByUser1);

        TestLike1ByUser1AndPost1 = saveLike(TestUser1, TestPost1ByUser1);
        TestLike2ByUser2AndPost1 = saveLike(TestUser2, TestPost1ByUser1);
    }

    private User saveUser(String email, String name, String nickname) {
        return userRepository.save(User.of(email, name, nickname, DEFAULT_PASSWORD, name + DEFAULT_COMMENT, DEFAULT_URL));
    }

    private Post savePost(User user) {
        return postRepository.save(Post.of(user.getNickname() + DEFAULT_CONTENTS, DEFAULT_URL, user));
    }

    private Comment saveComment(User user, Post post) {
        return commentRepository.save(Comment.of(DEFAULT_COMMENT, user, post));
    }

    private Like saveLike(User user, Post post) {
        return likeRepository.save(Like.of(user, post));
    }

}
