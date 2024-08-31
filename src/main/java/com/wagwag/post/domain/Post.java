package com.wagwag.post.domain;

import com.wagwag.global.entity.BaseTimeEntity;
import com.wagwag.post.domain.Enum.PostStatus;
import com.wagwag.relation.domain.PostCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column
    private String title;

    @Column
    private String videoUrl;

    @Column
    private String thumbnailUrl;

    @Column
    private Long viewCnt;

    @Column
    private Long shareCnt;

    @Enumerated(EnumType.STRING)
    private PostStatus status;

    @OneToMany(mappedBy ="postId", cascade = CascadeType.ALL)
    List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "postId", cascade = CascadeType.ALL)
    List<PostCategory> postCategoryList = new ArrayList<>();
}
