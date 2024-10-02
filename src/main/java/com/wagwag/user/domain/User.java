package com.wagwag.user.domain;

import com.wagwag.config.oauth.OauthInfo;
import com.wagwag.global.entity.BaseTimeEntity;
import com.wagwag.location.domain.Town;
import com.wagwag.post.domain.Comment;
import com.wagwag.post.domain.Post;
import com.wagwag.relation.domain.PostLike;
import com.wagwag.relation.domain.UserCategory;
import com.wagwag.user.domain.enums.*;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String userName;

    private int age;

    @Embedded
    private OauthInfo oauthInfo;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToOne
    @JoinColumn(name = "town_code")
    private Town townCode;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy= "userId", cascade= CascadeType.ALL)
    private List<PostLike> likeList = new ArrayList<>();

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<UserCategory> userCategoryList = new ArrayList<>();

    private User(OauthInfo oauthInfo){
        this.role = UserRole.USER;
        this.oauthInfo = oauthInfo;
    }

    public static User create(OauthInfo oauthInfo){
        return new User(oauthInfo);
    }

}
