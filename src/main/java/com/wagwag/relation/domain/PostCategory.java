package com.wagwag.relation.domain;

import com.wagwag.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCategory extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_category_id")
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="post_id")
    @Column(name ="post_id")
    private Long postId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="category_id")
    @Column(name = "category_id")
    private Long categoryId;
}
