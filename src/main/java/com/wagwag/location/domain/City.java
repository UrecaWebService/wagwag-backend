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
public class City {

    @Id
    private Long code;

    private String name;

    @OneToMany(mappedBy = "cityCode",cascade = CascadeType.ALL)
    private List<District> districtList = new ArrayList<>();
}
