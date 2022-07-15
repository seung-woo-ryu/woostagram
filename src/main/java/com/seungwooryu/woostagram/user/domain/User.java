package com.seungwooryu.woostagram.user.domain;

import com.seungwooryu.woostagram.common.domain.BaseEntity;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
public class User extends BaseEntity {

    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;
    @Column(name = "password", unique = true, nullable = false)
    private String password;
    @Column(name = "comment", unique = true, nullable = false)
    private String comment;
    @Column(name = "profile_url", unique = true, nullable = false)
    private String profileUrl;

    @Column(name = "created_at", unique = true, nullable = true)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", unique = true, nullable = true)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private User(String email, String name, String userName, String password, String comment, String profileUrl) {
        this.email = email;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.comment = comment;
        this.profileUrl = profileUrl;
    }
}


