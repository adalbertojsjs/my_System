package com.repository.My_System.domain.model;


import com.repository.My_System.domain.enums.EnumMilck;
import com.repository.My_System.domain.enums.EnumSize;
import com.repository.My_System.domain.enums.EnumTypeCoffee;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeItem {


    private UUID id;

    private EnumTypeCoffee drink;
    private EnumMilck milk;
    private EnumSize size;
    private int quantity;
}


