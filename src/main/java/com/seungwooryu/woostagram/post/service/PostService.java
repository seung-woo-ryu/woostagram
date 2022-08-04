package com.seungwooryu.woostagram.post.service;

import com.seungwooryu.woostagram.post.domain.Post;
import com.seungwooryu.woostagram.post.dto.PostDto;
import com.seungwooryu.woostagram.post.exception.PostNotFoundException;
import com.seungwooryu.woostagram.post.repository.PostRepository;
import com.seungwooryu.woostagram.user.domain.User;
import com.seungwooryu.woostagram.user.exception.AuthenticationException;
import com.seungwooryu.woostagram.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final FileService fileService;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long upload(PostDto postDto, String userEmail, String savedImagePath) {
        User user = userRepository.findByEmail(userEmail);
        Post newPost = Post.of(postDto.getContent(), savedImagePath, user);
        Post savedPost = postRepository.save(newPost);

        return savedPost.getId();
    }

    @Transactional
    public String delete(Long id, String email) {
        Post post = findById(id);

        final String imageUrl = post.getImageUrl();

        validateUser(post, email);
        postRepository.deleteById(id);

        return imageUrl;
    }

    private void validateUser(Post post, String email) {
        User findUser = userRepository.findByEmail(email);
        if (!post.isAuthor(findUser)) {
            throw new AuthenticationException();
        }
    }

    public Post findById(Long postId) {
        return postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
    }

    //ToDo: 수정 페이지(프론트)작업되었을 때
    public Long update(PostDto postDto, String loggedInUserEmail, Long id) {
        return 1L;
    }
}
