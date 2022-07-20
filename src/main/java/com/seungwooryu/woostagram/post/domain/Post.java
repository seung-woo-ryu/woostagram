package com.seungwooryu.woostagram.post.domain;

import com.seungwooryu.woostagram.common.domain.BaseEntity;
import com.seungwooryu.woostagram.user.domain.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "posts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contents", unique = false, nullable = false)
    private String contents;

    @Column(name = "image_url", unique = false, nullable = false)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name="author_id",referencedColumnName = "id")
    private User user;

    private Post(String contents, String imageUrl, User user) {
        this.contents = contents;
        this.imageUrl = imageUrl;
        this.user = user;
    }

    public static Post of(String contents, String imageUrl, User authorId) {
        return new Post(contents, imageUrl, authorId);
    }


    public void updateImageUrl(String imgUrl){
        this.imageUrl = imgUrl;
    }

    public void updateContents(String contents){
        this.contents = contents;
    }

}
