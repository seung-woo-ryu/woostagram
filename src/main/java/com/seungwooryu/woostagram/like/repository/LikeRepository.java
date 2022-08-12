package com.seungwooryu.woostagram.like.repository;

import com.seungwooryu.woostagram.like.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    @Query("select l from Like l where user.id = :userId and post.id = :postId")
    Optional<Like> findByUserIdAndPostId(@Param("userId") Long userId, @Param("postId") Long postId);

    boolean existsByUser_IdAndPost_Id(Long userId, Long postId);

    Long countByPost_id(Long postId);
}
