package com.seungwooryu.woostagram.post.domain;

import com.seungwooryu.woostagram.common.domain.BaseEntity;
import com.seungwooryu.woostagram.user.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Getter
@RequiredArgsConstructor
public class Comment extends BaseEntity {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contents", nullable = false, length = 1000)
    private final String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="author_id",referencedColumnName = "id")
    private final User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id",referencedColumnName = "id")
    private final Post post;
}
