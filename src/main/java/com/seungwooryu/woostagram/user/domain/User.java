package com.seungwooryu.woostagram.user.domain;

import com.seungwooryu.woostagram.common.domain.BaseEntity;
import com.seungwooryu.woostagram.user.dto.SignupDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@ToString
public class User extends BaseEntity {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nickname", unique = true, nullable = false)
    private String nickname;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "profile_url", nullable = false)
    private String profileUrl;

    private User(String email, String name, String nickname, String password, String comment, String profileUrl) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.comment = comment;
        this.profileUrl = profileUrl;
    }

    public static User of(String email, String name, String nickname, String password, String comment, String profileUrl) {
        return new User(email,name,nickname, password, comment, profileUrl);
    }

    public static User createUserBySignupDto(SignupDto signupDto) {
        String comment = " ";
        String profileUrl = "default_profile_url";

        return new User(signupDto.getEmail(), signupDto.getName(), signupDto.getNickname(),signupDto.getPassword(), comment, profileUrl);
    }

    public void updateComment(String comment){
        this.comment = comment;
    }
    public void updateProfileUrl(String profileUrl){
        this.profileUrl = profileUrl;
    }
}


