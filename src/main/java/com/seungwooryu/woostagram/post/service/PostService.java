package com.seungwooryu.woostagram.post.service;

import com.seungwooryu.woostagram.post.domain.Post;
import com.seungwooryu.woostagram.post.dto.PostDto;
import com.seungwooryu.woostagram.post.exception.PostNotFoundException;
import com.seungwooryu.woostagram.post.repository.PostRepository;
import com.seungwooryu.woostagram.user.domain.User;
import com.seungwooryu.woostagram.user.exception.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long upload(PostDto postDto, User user, String savedImagePath) {
        Post newPost = Post.of(postDto.getContent(), savedImagePath, user);
        Post savedPost = postRepository.save(newPost);
        return savedPost.getId();
    }

    @Transactional
    public String delete(Long postId, User sessionUser) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        final String imageUrl = post.getImageUrl();

        validateUser(post, sessionUser);
        postRepository.deleteById(postId);

        return imageUrl;
    }

    public void validateUser(Post post, User sessionUser) {
        if (!post.isAuthor(sessionUser)) {
            throw new AuthenticationException();
        }
    }

    @Transactional(readOnly = true)
    public Post findPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
    }

    //ToDo: 수정 페이지(프론트)작업되었을 때
    public Long update(PostDto postDto, String loggedInUserEmail, Long id) {
        return 1L;
    }
}
