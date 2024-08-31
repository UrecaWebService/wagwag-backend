package com.wagwag.post.domain;

import com.wagwag.global.entity.BaseTimeEntity;
import com.wagwag.post.domain.Enum.CommentStatus;
import com.wagwag.user.domain.User;
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
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;

    @Enumerated(EnumType.STRING)
    private CommentStatus status;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "post_id")
    @Column(name = "post_id")
    private Long postId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
    @Column(name = "user_id")
    private Long userId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "parent_id")
    @Column(name = "parent_id")
    private Long parentId;

    @OneToMany(mappedBy = "parentId")
    private List<Comment> childList = new ArrayList<>();
}
