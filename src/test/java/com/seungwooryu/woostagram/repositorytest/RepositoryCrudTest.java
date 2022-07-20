package com.seungwooryu.woostagram.repositorytest;

import com.seungwooryu.woostagram.post.domain.Comment;
import com.seungwooryu.woostagram.post.domain.Like;
import com.seungwooryu.woostagram.post.domain.Post;
import com.seungwooryu.woostagram.post.repository.CommentRepository;
import com.seungwooryu.woostagram.post.repository.LikeRepository;
import com.seungwooryu.woostagram.post.repository.PostRepository;
import com.seungwooryu.woostagram.user.domain.User;
import com.seungwooryu.woostagram.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RepositoryCrudTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    CommentRepository commentRepository;


    @Test
    public void create() {
        User user = User.of("tmddn646@naver.com","seungwooryu2","tmddn6452","vvee12","29nam","url_info");
        Post post = Post.of("first posts","img_url_info",user);
        Like like = Like.of(user,post);
        Comment comment = Comment.of("post is good",user,post);

        User savedUser = userRepository.save(user);
        Post savedPost = postRepository.save(post);
        Like savedLike = likeRepository.save(like);
        Comment savedComment = commentRepository.save(comment);

        assertThat(savedUser).isEqualTo(user);
        assertThat(savedPost).isEqualTo(post);
        assertThat(savedLike).isEqualTo(like);
        assertThat(savedComment).isEqualTo(comment);
    }

    @Test
    public void findOne() {
        User getUser = userRepository.findByEmail("tmddn645@naver.com");
        assertThat(getUser).isNotNull();

        List<Post> getPosts = postRepository.findByAuthorId(getUser.getId());
        assertThat(getPosts).isNotNull();

        Like getLike = likeRepository.findByUserIdAndPostId(getUser.getId(), getPosts.get(0).getId());
        assertThat(getLike).isNotNull();

        Comment getComment = commentRepository.findByUserIdAndPostId(getUser.getId(),getPosts.get(0).getId());
        assertThat(getComment).isNotNull();
    }

    @Test
    public void findAll() {
        List<User> getUsers = userRepository.findAll();
        assertThat(getUsers).hasSize(4);

        List<Like> getLikes = likeRepository.findAll();
        assertThat(getLikes).hasSize(1);

        List<Comment> getcomments = commentRepository.findAll();
        assertThat(getcomments).hasSize(1);

        List<Post> getPosts = postRepository.findAll();
        assertThat(getPosts).hasSize(3);
    }

    @Test
    public void update() {
        User getUser = userRepository.findByEmail("tmddn645@naver.com");
        Post getPost = postRepository.findByAuthorId(getUser.getId()).get(0);

        Comment getComment = commentRepository.findByUserIdAndPostId(getUser.getId(),getPost.getId());

        String updatedComments = "this is updated comments!";
        getComment.updateContents(updatedComments);

        Comment targetComment = commentRepository.save(getComment);

        assertThat(targetComment.getContents()).isEqualTo(updatedComments);
    }

    @Test
    public void delete() {
        User getUser = userRepository.findByEmail("tmddn645@naver.com");
        Post getPost = postRepository.findByAuthorId(getUser.getId()).get(0);
        Like getLike = likeRepository.findByUserIdAndPostId(getUser.getId(),getPost.getId());
        Comment getComment = commentRepository.findByUserIdAndPostId(getUser.getId(),getPost.getId());

        commentRepository.delete(getComment);
        likeRepository.delete(getLike);
        postRepository.delete(getPost);
        userRepository.delete(getUser);
    }
}
