package com.wagwag.location.domain;


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
public class District {

    @Id
    private Long code;

    private String name;

//    @ManyToOne
//    @JoinColumn(name = "city_code")
    @Column(name ="city_code")
    private Long cityCode;

    @OneToMany(mappedBy = "districtCode",cascade = CascadeType.ALL)
    private List<Town> townList = new ArrayList<>();

}
