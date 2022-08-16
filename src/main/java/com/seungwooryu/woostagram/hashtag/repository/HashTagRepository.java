package com.seungwooryu.woostagram.hashtag.repository;

import com.seungwooryu.woostagram.hashtag.domain.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashTagRepository extends JpaRepository<HashTag, Long> {
}
