package com.seungwooryu.woostagram.post.domain;

import com.seungwooryu.woostagram.common.domain.BaseEntity;
import com.seungwooryu.woostagram.user.domain.User;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Getter
public class Comment extends BaseEntity {
    @Column(name = "contents", unique = false, nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(name="author_id",referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name="post_id",referencedColumnName = "id")
    private Post post;
}
