package com.wagwag.user.domain;

import com.wagwag.global.entity.BaseTimeEntity;
import com.wagwag.post.domain.Comment;
import com.wagwag.relation.domain.PostLike;
import com.wagwag.relation.domain.UserCategory;
import com.wagwag.user.domain.Enum.UserStatus;
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

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy= "userId", cascade= CascadeType.ALL)
    private List<PostLike> likeList = new ArrayList<>();

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<UserCategory> userCategoryList = new ArrayList<>();
}
