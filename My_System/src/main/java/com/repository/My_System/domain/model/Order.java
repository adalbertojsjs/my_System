package com.repository.My_System.domain.model;

import com.repository.My_System.domain.enums.EnumLocation;
import com.repository.My_System.domain.enums.EnumSize;
import com.repository.My_System.domain.enums.EnumStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private UUID id;
    private EnumLocation enumLocation;
    private  List<CoffeeItem> items;
    private EnumStatus status;
//    int quantity;

}


