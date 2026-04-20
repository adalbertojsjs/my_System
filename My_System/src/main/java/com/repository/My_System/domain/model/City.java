package com.repository.My_System.domain.model;


import com.repository.My_System.domain.enums.EnumContinents;
import lombok.*;
import lombok.experimental.SuperBuilder;



@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class City {

    private  Long id;

    private  String name;

    private String countries;

    private EnumContinents continents;

}
