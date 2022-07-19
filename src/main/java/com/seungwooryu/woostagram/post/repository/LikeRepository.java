package com.seungwooryu.woostagram.post.repository;

import com.seungwooryu.woostagram.post.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {
}
