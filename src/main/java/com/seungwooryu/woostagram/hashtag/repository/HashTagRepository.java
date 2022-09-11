package com.seungwooryu.woostagram.hashtag.repository;

import com.seungwooryu.woostagram.hashtag.domain.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HashTagRepository extends JpaRepository<HashTag, Long> {
    List<HashTag> findAllByTag_NameLike(String hashtag);
}
