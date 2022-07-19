package com.seungwooryu.woostagram.post.repository;

import com.seungwooryu.woostagram.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
