package com.seungwooryu.woostagram.post.repository;

import com.seungwooryu.woostagram.post.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
