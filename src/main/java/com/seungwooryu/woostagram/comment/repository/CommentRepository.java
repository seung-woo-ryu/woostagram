package com.seungwooryu.woostagram.comment.repository;

import com.seungwooryu.woostagram.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c from Comment c where user.id = :userId and post.id = :postId")
    Comment findByUserIdAndPostId(@Param("userId") Long userId, @Param("postId") Long postId);

    @Query("select c from Comment c where post.id = :postId order by c.createdAt desc")
    List<Comment> findAllByPostId(@Param("postId") Long postId);
}
