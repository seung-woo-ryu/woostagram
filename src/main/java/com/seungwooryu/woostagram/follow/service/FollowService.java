package com.seungwooryu.woostagram.follow.service;

import com.seungwooryu.woostagram.follow.domain.Follow;
import com.seungwooryu.woostagram.follow.exception.FollowNotFoundException;
import com.seungwooryu.woostagram.follow.repository.FollowRepository;
import com.seungwooryu.woostagram.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;

    public void create(User follower, User followedUser) {
        if (checkNotExistsFollow(follower.getId(), followedUser.getId())) {
            Follow newFollow = Follow.of(follower, followedUser);
            followRepository.save(newFollow);
        }
    }

    public boolean delete(User follower, User followedUser) {
        Follow follow = findByFromIdAndToId(follower.getId(), followedUser.getId());
        followRepository.delete(follow);

        return true;
    }

    private boolean checkNotExistsFollow(Long followerId, Long followedUserId) {
        return !followRepository.existsByFromUser_IdAndToUser_Id(followerId, followedUserId);
    }

    private Follow findByFromIdAndToId(Long followerId, Long followedUserId) {
        return followRepository.findByFromUser_IdAndToUser_Id(followerId, followedUserId)
                .orElseThrow(FollowNotFoundException::new);
    }
}
