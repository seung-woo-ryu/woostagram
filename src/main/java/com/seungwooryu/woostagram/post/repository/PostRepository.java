package com.seungwooryu.woostagram.post.repository;

import com.seungwooryu.woostagram.post.domain.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p where user.id = :userId")
    List<Post> findAllByAuthorId(@Param("userId") Long userId);

    @Query("select p from Post p where user.id In :userIdList order by p.id desc")
    List<Post> findAllByAuthorIdIn(@Param("userIdList") List<Long> userIdList, Pageable page);

    @Query("select p from Post p where user.id In :userIdList and id < :postId order by p.id desc")
    List<Post> findAllByIdLessThanOrderByIdDescAndAuthorIdIn(@Param("userIdList") List<Long> userIdList, @Param("postId") Long postId, Pageable page);

}
