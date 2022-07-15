package com.seungwooryu.woostagram.post.domain;

import com.seungwooryu.woostagram.common.domain.BaseEntity;
import com.seungwooryu.woostagram.user.domain.User;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Getter
public class Post extends BaseEntity {

    @Column(name = "contents", unique = false, nullable = false)
    private String contents;

    @Column(name = "image_url", unique = false, nullable = false)
    private String imageUrl;

    @Column(name = "created_at", unique = true, nullable = true)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", unique = true, nullable = true)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

/*
    @ManyToOne
    @JoinColumn(name="author_id",referencedColumnName = "id")
    private User user;
*/
}
