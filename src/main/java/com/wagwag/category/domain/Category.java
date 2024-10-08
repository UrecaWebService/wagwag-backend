package com.wagwag.category.domain;

import com.wagwag.relation.domain.PostCategory;
import com.wagwag.relation.domain.UserCategory;
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
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;

    @OneToMany(mappedBy = "categoryId", cascade = CascadeType.ALL)
    private List<PostCategory> postCategoryList = new ArrayList<>();

    @OneToMany(mappedBy = "categoryId", cascade = CascadeType.ALL)
    private List<UserCategory> userCategoryList = new ArrayList<>();
}
