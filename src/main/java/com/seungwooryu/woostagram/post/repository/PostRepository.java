package com.seungwooryu.woostagram.post.repository;

import com.seungwooryu.woostagram.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p where user.id = :userId")
    List<Post> findAllByAuthorId(@Param("userId") Long userId);
}
