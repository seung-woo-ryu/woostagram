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

    public static User testUser1;
    public static User testUser2;
    public static User testUser3;
    public static Post testPost1ByUser1;
    public static Post testPost2ByUser1;
    public static Post testPost3ByUser1;
    public static Post testPost1ByUser2;
    public static Post testPost2ByUser2;
    public static Post testPost3ByUser2;
    public static Post testPost1ByUser3;
    public static Post testPost2ByUser3;
    public static Post testPost3ByUser3;
    public static Comment testComment1ByUser1AndPost1;
    public static Comment testComment2ByUser2AndPost1;
    public static Comment testComment1ByUser3AndPost2;
    public static Like testLike1ByUser1AndPost1;
    public static Like testLike2ByUser2AndPost1;

    public static Follow testFollow1FromUser1toUser2;
    public static Follow testFollow2FromUser1toUser3;

    public static Tag testTag1;
    public static Tag testTag2;
    public static Tag testTag3;
    public static Tag testTag5;
    public static Tag testTag6;
    public static Tag testTag7;
    public static Tag testTag8;
    public static Tag testTag9;

    public static HashTag testHashTagByPost1AndTag1;
    public static HashTag testHashTagByPost1AndTag5;
    public static HashTag testHashTagByPost1AndTag7;
    public static HashTag testHashTagByPost2AndTag2;
    public static HashTag testHashTagByPost2AndTag5;
    public static HashTag testHashTagByPost2AndTag8;
    public static HashTag testHashTagByPost1AndTag3;
    public static HashTag testHashTagByPost1AndTag6;
    public static HashTag testHashTagByPost1AndTag9;

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
        testUser1 = saveUser("tmddn645@naver.com", "seungwoo", "loginUser");
        testUser2 = saveUser("catLove1@naver.com", "kanghunmo", "catmeow");
        testUser3 = saveUser("dogLove1@naver.com", "kimhunmo", "dogwalwal");

        testPost1ByUser1 = savePost(testUser1, createContent(1, 1, 1));
        testPost2ByUser1 = savePost(testUser1, createContent(2, 1, 2));
        testPost3ByUser1 = savePost(testUser1);

        testPost1ByUser2 = savePost(testUser2, createContent(4, 2, 3));
        testPost2ByUser2 = savePost(testUser2);
        testPost3ByUser2 = savePost(testUser2);

        testPost1ByUser3 = savePost(testUser3);
        testPost2ByUser3 = savePost(testUser3);
        testPost3ByUser3 = savePost(testUser3);

        testTag1 = saveTag("#post1");
        testTag2 = saveTag("#post2");
        testTag3 = saveTag("#post4");
        testTag5 = saveTag("#user1");
        testTag6 = saveTag("#user2");
        testTag7 = saveTag("#content1");
        testTag8 = saveTag("#content2");
        testTag9 = saveTag("#content3");

        testHashTagByPost1AndTag1 = saveHashTag(testPost1ByUser1, testTag1);
        testHashTagByPost1AndTag5 = saveHashTag(testPost1ByUser1, testTag5);
        testHashTagByPost1AndTag7 = saveHashTag(testPost1ByUser1, testTag7);
        testHashTagByPost2AndTag2 = saveHashTag(testPost2ByUser1, testTag2);
        testHashTagByPost2AndTag5 = saveHashTag(testPost2ByUser1, testTag5);
        testHashTagByPost2AndTag8 = saveHashTag(testPost2ByUser1, testTag8);
        testHashTagByPost1AndTag3 = saveHashTag(testPost1ByUser2, testTag3);
        testHashTagByPost1AndTag6 = saveHashTag(testPost1ByUser2, testTag6);
        testHashTagByPost1AndTag9 = saveHashTag(testPost1ByUser2, testTag9);

        testComment1ByUser1AndPost1 = saveComment(testUser1, testPost1ByUser1);
        testComment2ByUser2AndPost1 = saveComment(testUser2, testPost1ByUser1);

        testComment1ByUser3AndPost2 = saveComment(testUser3, testPost2ByUser1);

        testLike1ByUser1AndPost1 = saveLike(testUser1, testPost1ByUser1);
        testLike2ByUser2AndPost1 = saveLike(testUser2, testPost1ByUser1);

        testFollow1FromUser1toUser2 = saveFollow(testUser1, testUser2);
        testFollow2FromUser1toUser3 = saveFollow(testUser1, testUser1);
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
