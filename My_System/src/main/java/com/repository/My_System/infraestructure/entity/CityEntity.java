package com.repository.My_System.infraestructure.entity;

import com.repository.My_System.domain.enums.EnumContinents;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@AllArgsConstructor
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @JoinColumn(name = "name")
    private  String name;

    @JoinColumn(name = "countries")
    private String countries;

    @JoinColumn(name = "continetns")
    private EnumContinents continents;

    public CityEntity(){
    }
}
