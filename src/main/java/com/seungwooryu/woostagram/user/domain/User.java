package com.seungwooryu.woostagram.user.domain;

import com.seungwooryu.woostagram.common.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
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

    public User(String email, String name, String nickname, String password, String comment, String profileUrl) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.comment = comment;
        this.profileUrl = profileUrl;
    }

    public void updateComment(String comment){
        this.comment = comment;
    }
    public void updateProfileUrl(String profileUrl){
        this.profileUrl = profileUrl;
    }
}


