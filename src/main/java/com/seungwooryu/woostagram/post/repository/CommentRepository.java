package com.seungwooryu.woostagram.post.repository;

import com.seungwooryu.woostagram.post.domain.Comment;
import com.seungwooryu.woostagram.post.domain.Post;
import com.seungwooryu.woostagram.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query("select c from Comment c where user.id = :userId and post.id = :postId")
    Comment findByUserIdAndPostId(@Param("userId") Long userId, @Param("postId") Long postId);

}
