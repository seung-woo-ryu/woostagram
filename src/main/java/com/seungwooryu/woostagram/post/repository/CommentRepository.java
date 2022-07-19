package com.seungwooryu.woostagram.post.repository;

import com.seungwooryu.woostagram.post.domain.Comment;
import com.seungwooryu.woostagram.post.domain.Post;
import com.seungwooryu.woostagram.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    Comment findByAuthorIdAndPostId(User user, Post post);
}
