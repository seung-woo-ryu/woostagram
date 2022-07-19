package com.seungwooryu.woostagram.post.domain;

import com.seungwooryu.woostagram.common.domain.BaseEntity;
import com.seungwooryu.woostagram.user.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name ="likes")
@Getter
@RequiredArgsConstructor
public class Like extends BaseEntity {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="author_id",referencedColumnName = "id")
    private final User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id",referencedColumnName = "id")
    private final Post post;
}
