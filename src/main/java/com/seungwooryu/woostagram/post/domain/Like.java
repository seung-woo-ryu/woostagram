package com.seungwooryu.woostagram.post.domain;

import com.seungwooryu.woostagram.common.domain.BaseEntity;
import com.seungwooryu.woostagram.user.domain.User;
import lombok.Getter;

import javax.persistence.*;


@Entity
@Table(name ="likes")
@Getter
public class Like extends BaseEntity {
/*
    @ManyToOne
    @JoinColumn(name="author_id",referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name="post_id",referencedColumnName = "id")
    private Post post;
*/
}
