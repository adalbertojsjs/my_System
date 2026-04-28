package com.repository.My_System.infraestructure.dto.http.request;

import com.repository.My_System.domain.enums.EnumLocation;
import com.repository.My_System.domain.model.CoffeeItem;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class OrderRequest {

    private List<CoffeeItem> coffeeItem;

    private EnumLocation enumLocation;

}
