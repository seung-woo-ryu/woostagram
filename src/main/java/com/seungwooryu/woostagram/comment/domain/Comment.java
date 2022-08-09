package com.seungwooryu.woostagram.comment.domain;

import com.seungwooryu.woostagram.common.domain.BaseEntity;
import com.seungwooryu.woostagram.post.domain.Post;
import com.seungwooryu.woostagram.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "comments", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"author_id", "post_id"})
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Comment extends BaseEntity {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contents", nullable = false, length = 1000)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    private Comment(String contents, User user, Post post) {
        this.contents = contents;
        this.user = user;
        this.post = post;
    }

    public static Comment of(String contents, User user, Post post) {
        return new Comment(contents, user, post);
    }

    public void updateContents(String contents) {
        this.contents = contents;
    }

    public boolean isAuthor(User sessionUser) {
        return this.user.getId().equals(sessionUser.getId());
    }
}
