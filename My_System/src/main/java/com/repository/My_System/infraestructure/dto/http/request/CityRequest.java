package com.repository.My_System.infraestructure.dto.http.request;

import com.repository.My_System.domain.enums.EnumContinents;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class CityRequest {

    private String name;

    private String contries;

    private EnumContinents continents;

}
