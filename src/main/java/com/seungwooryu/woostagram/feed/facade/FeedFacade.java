package com.seungwooryu.woostagram.feed.facade;

import com.seungwooryu.woostagram.comment.dto.CommentDto;
import com.seungwooryu.woostagram.comment.repository.CommentRepository;
import com.seungwooryu.woostagram.feed.dto.FeedDto;
import com.seungwooryu.woostagram.follow.service.FollowService;
import com.seungwooryu.woostagram.like.repository.LikeRepository;
import com.seungwooryu.woostagram.post.domain.Post;
import com.seungwooryu.woostagram.post.repository.PostRepository;
import com.seungwooryu.woostagram.user.domain.User;
import com.seungwooryu.woostagram.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FeedFacade {
    private final UserService userService;
    private final FollowService followService;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    @Transactional(readOnly = true)
    public List<FeedDto> get(String loggedInUserEmail, Long postId, Pageable page) {
        User sessionUser = userService.findUserByEmail(loggedInUserEmail);

        List<Long> followingUserIdList = findFollowingUserIdLIst(sessionUser);

        List<Post> postListByFollowing = getPostByCursorBasedPagination(followingUserIdList, postId, page);

        return createFeedDtoList(postListByFollowing);
    }

    private List<Post> getPostByCursorBasedPagination(List<Long> followingUserIdList, Long postId, Pageable page) {
        return Optional.ofNullable(postId).isPresent() ?
                postRepository.findAllByIdLessThanOrderByIdDescAndAuthorIdIn(followingUserIdList, postId, page) :
                postRepository.findAllByAuthorIdIn(followingUserIdList, page);
    }

    private List<FeedDto> createFeedDtoList(List<Post> postListByFollowing) {
        return postListByFollowing
                .stream()
                .map((post) -> createFeedDto(post))
                .collect(Collectors.toList());
    }

    private FeedDto createFeedDto(Post post) {
        List<CommentDto> commentDtoList = createCommentDtoList(post);
        Long likeCount = likeRepository.countByPost_id(post.getId());
        return FeedDto.of(commentDtoList, likeCount, post);
    }

    private List<CommentDto> createCommentDtoList(Post post) {
        return commentRepository.findAllByPostId(post.getId())
                .stream()
                .map(CommentDto::of)
                .collect(Collectors.toList());
    }

    private List<Long> findFollowingUserIdLIst(User sessionUser) {
        return followService.findAllByFromId(sessionUser.getId())
                .stream()
                .map((follow) -> follow.getToUser().getId())
                .collect(Collectors.toList());
    }
}
