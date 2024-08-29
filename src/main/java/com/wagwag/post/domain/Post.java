package com.wagwag.post.domain;

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
public class Post {
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

    @Column
    private Long status;

    @OneToMany(mappedBy ="post", cascade = CascadeType.ALL)
    List<Comment> commentList = new ArrayList<>();
}
