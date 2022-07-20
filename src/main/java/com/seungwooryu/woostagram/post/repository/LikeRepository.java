package com.seungwooryu.woostagram.post.repository;

import com.seungwooryu.woostagram.post.domain.Like;
import com.seungwooryu.woostagram.post.domain.Post;
import com.seungwooryu.woostagram.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
    @Query("select l from Like l where user.id = :userId and post.id = :postId")
    Like findByUserIdAndPostId(@Param("userId") Long userId, @Param("postId") Long postId);
}
