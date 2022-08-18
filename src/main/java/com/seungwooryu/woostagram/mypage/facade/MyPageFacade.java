package com.seungwooryu.woostagram.mypage.facade;

import com.seungwooryu.woostagram.follow.service.FollowService;
import com.seungwooryu.woostagram.mypage.dto.PageDto;
import com.seungwooryu.woostagram.post.dto.SubPostDto;
import com.seungwooryu.woostagram.post.service.PostService;
import com.seungwooryu.woostagram.user.domain.User;
import com.seungwooryu.woostagram.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MyPageFacade {
    private final UserService userService;
    private final PostService postService;
    private final FollowService followService;

    public PageDto get(String loggedInUserEmail, String nickname) {
        User sessionUser = userService.findUserByEmail(loggedInUserEmail);
        User findUser = userService.findUserByNickname(nickname);

        List<SubPostDto> subPostDtoList = postService.findPostByNickname(nickname)
                .stream()
                .map(post -> SubPostDto.of(post))
                .collect(Collectors.toList());

        return PageDto.of(findUser,
                subPostDtoList,
                sessionUser.isAuthor(findUser),
                postService.countPost(nickname),
                followService.countFollower(nickname),
                followService.countFollowing(nickname));
    }
}
