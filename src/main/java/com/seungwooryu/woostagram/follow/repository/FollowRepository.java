package com.seungwooryu.woostagram.follow.repository;

import com.seungwooryu.woostagram.follow.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    boolean existsByFromUser_IdAndToUser_Id(Long followerId, Long followedUserId);

    Optional<Follow> findByFromUser_IdAndToUser_Id(Long followerId, Long followedUserId);

    List<Follow> findAllByFromUser_Id(Long fromId);

    Long countByFromUser_Nickname(String nickname);

    Long countByToUser_Nickname(String nickname);
}
