package com.seungwooryu.woostagram.post.domain;

import com.seungwooryu.woostagram.common.domain.BaseEntity;
import com.seungwooryu.woostagram.user.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "posts")
@Getter
@RequiredArgsConstructor
public class Post extends BaseEntity {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contents", unique = false, nullable = false)
    private final String contents;

    @Column(name = "image_url", unique = false, nullable = false)
    private final String imageUrl;

    @ManyToOne
    @JoinColumn(name="author_id",referencedColumnName = "id")
    private final User user;
}
