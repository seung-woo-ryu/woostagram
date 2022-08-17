package com.seungwooryu.woostagram.common.datainitializer;

import com.seungwooryu.woostagram.comment.domain.Comment;
import com.seungwooryu.woostagram.comment.repository.CommentRepository;
import com.seungwooryu.woostagram.follow.domain.Follow;
import com.seungwooryu.woostagram.follow.repository.FollowRepository;
import com.seungwooryu.woostagram.hashtag.domain.HashTag;
import com.seungwooryu.woostagram.hashtag.repository.HashTagRepository;
import com.seungwooryu.woostagram.like.domain.Like;
import com.seungwooryu.woostagram.like.repository.LikeRepository;
import com.seungwooryu.woostagram.post.domain.Post;
import com.seungwooryu.woostagram.post.repository.PostRepository;
import com.seungwooryu.woostagram.tag.domain.Tag;
import com.seungwooryu.woostagram.tag.repository.TagRepository;
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
    private final FollowRepository followRepository;
    private final TagRepository tagRepository;
    private final HashTagRepository hashTagRepository;

    public static User TestUser1;
    public static User TestUser2;
    public static User TestUser3;
    public static Post TestPost1ByUser1;
    public static Post TestPost2ByUser1;
    public static Post TestPost3ByUser1;
    public static Post TestPost1ByUser2;
    public static Post TestPost2ByUser2;
    public static Post TestPost3ByUser2;
    public static Post TestPost1ByUser3;
    public static Post TestPost2ByUser3;
    public static Post TestPost3ByUser3;
    public static Comment TestComment1ByUser1AndPost1;
    public static Comment TestComment2ByUser2AndPost1;
    public static Comment TestComment1ByUser3AndPost2;
    public static Like TestLike1ByUser1AndPost1;
    public static Like TestLike2ByUser2AndPost1;

    public static Follow TestFollow1FromUser1toUser2;
    public static Follow TestFollow2FromUser1toUser3;

    public static Tag TestTag1;
    public static Tag TestTag2;
    public static Tag TestTag3;
    public static Tag TestTag5;
    public static Tag TestTag6;
    public static Tag TestTag7;
    public static Tag TestTag8;
    public static Tag TestTag9;

    public static HashTag TestHashTagByPost1AndTag1;
    public static HashTag TestHashTagByPost1AndTag5;
    public static HashTag TestHashTagByPost1AndTag7;
    public static HashTag TestHashTagByPost2AndTag2;
    public static HashTag TestHashTagByPost2AndTag5;
    public static HashTag TestHashTagByPost2AndTag8;
    public static HashTag TestHashTagByPost1AndTag3;
    public static HashTag TestHashTagByPost1AndTag6;
    public static HashTag TestHashTagByPost1AndTag9;

    private String tagString11 = "#post1";
    private String tagString12 = "#user1";
    private String tagString13 = "#content1";
    private String tagString22 = "#user2";
    private String tagString23 = "#content2";
    private String tagString31 = "#post3";
    private String tagString32 = "#user3";

    private String createContent(int postNum, int userNum, int contentNum) {
        return "#post" + postNum + " #user" + userNum + " #content" + contentNum;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        TestUser1 = saveUser("tmddn645@naver.com", "seungwoo", "loginUser");
        TestUser2 = saveUser("catLove1@naver.com", "kanghunmo", "catmeow");
        TestUser3 = saveUser("dogLove1@naver.com", "kimhunmo", "dogwalwal");

        TestPost1ByUser1 = savePost(TestUser1, createContent(1, 1, 1));
        TestPost2ByUser1 = savePost(TestUser1, createContent(2, 1, 2));
        TestPost3ByUser1 = savePost(TestUser1);

        TestPost1ByUser2 = savePost(TestUser2, createContent(4, 2, 3));
        TestPost2ByUser2 = savePost(TestUser2);
        TestPost3ByUser2 = savePost(TestUser2);

        TestPost1ByUser3 = savePost(TestUser3);
        TestPost2ByUser3 = savePost(TestUser3);
        TestPost3ByUser3 = savePost(TestUser3);

        TestTag1 = saveTag("#post1");
        TestTag2 = saveTag("#post2");
        TestTag3 = saveTag("#post4");
        TestTag5 = saveTag("#user1");
        TestTag6 = saveTag("#user2");
        TestTag7 = saveTag("#content1");
        TestTag8 = saveTag("#content2");
        TestTag9 = saveTag("#content3");

        TestHashTagByPost1AndTag1 = saveHashTag(TestPost1ByUser1, TestTag1);
        TestHashTagByPost1AndTag5 = saveHashTag(TestPost1ByUser1, TestTag5);
        TestHashTagByPost1AndTag7 = saveHashTag(TestPost1ByUser1, TestTag7);
        TestHashTagByPost2AndTag2 = saveHashTag(TestPost2ByUser1, TestTag2);
        TestHashTagByPost2AndTag5 = saveHashTag(TestPost2ByUser1, TestTag5);
        TestHashTagByPost2AndTag8 = saveHashTag(TestPost2ByUser1, TestTag8);
        TestHashTagByPost1AndTag3 = saveHashTag(TestPost1ByUser2, TestTag3);
        TestHashTagByPost1AndTag6 = saveHashTag(TestPost1ByUser2, TestTag6);
        TestHashTagByPost1AndTag9 = saveHashTag(TestPost1ByUser2, TestTag9);

        TestComment1ByUser1AndPost1 = saveComment(TestUser1, TestPost1ByUser1);
        TestComment2ByUser2AndPost1 = saveComment(TestUser2, TestPost1ByUser1);

        TestComment1ByUser3AndPost2 = saveComment(TestUser3, TestPost2ByUser1);

        TestLike1ByUser1AndPost1 = saveLike(TestUser1, TestPost1ByUser1);
        TestLike2ByUser2AndPost1 = saveLike(TestUser2, TestPost1ByUser1);

        TestFollow1FromUser1toUser2 = saveFollow(TestUser1, TestUser2);
        TestFollow2FromUser1toUser3 = saveFollow(TestUser1, TestUser1);
    }

    private Follow saveFollow(User testUser1, User testUser2) {
        return followRepository.save(Follow.of(testUser1, testUser2));
    }

    private User saveUser(String email, String name, String nickname) {
        return userRepository.save(User.of(email, name, nickname, DEFAULT_PASSWORD, name + DEFAULT_COMMENT, DEFAULT_URL));
    }

    private Post savePost(User user, String content) {
        return postRepository.save(Post.of(content, DEFAULT_URL, user));
    }

    private Post savePost(User user) {
        return savePost(user, user.getNickname() + DEFAULT_CONTENTS);
    }

    private Comment saveComment(User user, Post post) {
        return commentRepository.save(Comment.of(DEFAULT_COMMENT, user, post));
    }

    private Like saveLike(User user, Post post) {
        return likeRepository.save(Like.of(user, post));
    }

    private Tag saveTag(String name) {
        return tagRepository.save(Tag.of(name));
    }

    private HashTag saveHashTag(Post post, Tag tag) {
        return hashTagRepository.save(HashTag.of(post, tag));
    }

}
