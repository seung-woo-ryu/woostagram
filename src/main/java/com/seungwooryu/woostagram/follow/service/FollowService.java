package com.seungwooryu.woostagram.follow.service;

import com.seungwooryu.woostagram.follow.domain.Follow;
import com.seungwooryu.woostagram.follow.exception.FollowNotFoundException;
import com.seungwooryu.woostagram.follow.repository.FollowRepository;
import com.seungwooryu.woostagram.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;

    public void create(User fromUser, User toUser) {
        if (checkNotExistsFollow(fromUser.getId(), toUser.getId())) {
            Follow newFollow = Follow.of(fromUser, toUser);
            followRepository.save(newFollow);
        }
    }

    public boolean delete(User fromUser, User toUser) {
        Follow follow = findByFromIdAndToId(fromUser.getId(), toUser.getId());
        followRepository.delete(follow);

        return true;
    }

    private boolean checkNotExistsFollow(Long fromId, Long toId) {
        return !followRepository.existsByFromUser_IdAndToUser_Id(fromId, toId);
    }

    private Follow findByFromIdAndToId(Long fromId, Long toId) {
        return followRepository.findByFromUser_IdAndToUser_Id(fromId, toId)
                .orElseThrow(FollowNotFoundException::new);
    }


    public List<Follow> findAllByFromId(Long fromId) {
        return followRepository.findAllByFromUser_Id(fromId);
    }
}
