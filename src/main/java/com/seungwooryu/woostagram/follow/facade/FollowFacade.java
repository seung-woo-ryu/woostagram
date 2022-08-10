package com.seungwooryu.woostagram.follow.facade;

import com.seungwooryu.woostagram.follow.service.FollowService;
import com.seungwooryu.woostagram.user.domain.User;
import com.seungwooryu.woostagram.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FollowFacade {
    private final UserService userService;
    private final FollowService followService;

    public void create(String loggedInUserEmail, Long followedUserId) {
        User followedUser = userService.findUserById(followedUserId);
        User follower = userService.findUserByEmail(loggedInUserEmail);

        followService.create(follower, followedUser);
    }

    public boolean delete(String loggedInUserEmail, Long followedUserId) {
        User followedUser = userService.findUserById(followedUserId);
        User follower = userService.findUserByEmail(loggedInUserEmail);

        return followService.delete(follower, followedUser);
    }


}
