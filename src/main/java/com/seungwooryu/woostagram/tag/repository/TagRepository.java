package com.seungwooryu.woostagram.tag.repository;

import com.seungwooryu.woostagram.tag.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findAllByNameIn(List<String> tagStringList);
}
