package com.seungwooryu.woostagram.post.repository;

import com.seungwooryu.woostagram.post.domain.Like;
import com.seungwooryu.woostagram.post.domain.Post;
import com.seungwooryu.woostagram.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
    Like findByAuthorIdAndPostId(User user, Post post);
}
