package com.seungwooryu.woostagram.follow.repository;

import com.seungwooryu.woostagram.follow.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    boolean existsByFromUser_IdAndToUser_Id(Long followerId, Long followedUserId);

    Optional<Follow> findByFromUser_IdAndToUser_Id(Long followerId, Long followedUserId);
}
