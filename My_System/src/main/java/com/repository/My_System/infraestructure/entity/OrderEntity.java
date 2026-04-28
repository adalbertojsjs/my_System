package com.repository.My_System.infraestructure.entity;


import com.repository.My_System.domain.enums.EnumLocation;
import com.repository.My_System.domain.enums.EnumStatus;
import com.repository.My_System.domain.model.CoffeeItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private EnumLocation location;

    @Enumerated(EnumType.STRING)
    @NotNull
    private EnumStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CoffeeItemEntity> items;

//    @NotNull
//    private Integer quantity;


}
