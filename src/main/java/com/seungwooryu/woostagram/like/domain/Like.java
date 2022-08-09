package com.seungwooryu.woostagram.like.domain;

import com.seungwooryu.woostagram.common.domain.BaseEntity;
import com.seungwooryu.woostagram.post.domain.Post;
import com.seungwooryu.woostagram.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


@Entity
@Table(name = "likes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"author_id", "post_id"})
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like extends BaseEntity {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    private Like(User user, Post post) {
        this.user = user;
        this.post = post;
    }

    public static Like of(User user, Post post) {
        return new Like(user, post);
    }
}
