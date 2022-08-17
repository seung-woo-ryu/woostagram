package com.seungwooryu.woostagram.like.service;

import com.seungwooryu.woostagram.like.domain.Like;
import com.seungwooryu.woostagram.like.exception.LikeNotExistsException;
import com.seungwooryu.woostagram.like.repository.LikeRepository;
import com.seungwooryu.woostagram.post.domain.Post;
import com.seungwooryu.woostagram.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;

    @Transactional
    public void create(User sessionUser, Post post) {
        if (!checkLikeExists(sessionUser.getId(), post.getId())) {
            Like newLike = Like.of(sessionUser, post);
            likeRepository.save(newLike);
        }
    }

    @Transactional
    public boolean delete(User sessionUser, Post post) {
        Like like = likeRepository.findByUserIdAndPostId(sessionUser.getId(), post.getId())
                .orElseThrow(LikeNotExistsException::new);
        likeRepository.delete(like);
        return true;
    }

    @Transactional(readOnly = true)
    private boolean checkLikeExists(Long userId, Long postId) {
        return likeRepository.existsByUser_IdAndPost_Id(userId, postId);
    }
}
