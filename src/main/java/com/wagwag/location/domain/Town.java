package com.wagwag.location.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Town{

    @Id
    private Long code;

    private String name;

//    @ManyToOne
//    @JoinColumn(name = "district_code")
    @Column(name = "district_code")
    private Long districtCode;


}
