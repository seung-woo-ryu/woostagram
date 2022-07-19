package com.seungwooryu.woostagram.user.domain;

import com.seungwooryu.woostagram.common.domain.BaseEntity;
import lombok.Getter;
import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
public class User extends BaseEntity {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "nickname",nullable = false)
    private String nickname;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "profile_url", nullable = false)
    private String profileUrl;
}


