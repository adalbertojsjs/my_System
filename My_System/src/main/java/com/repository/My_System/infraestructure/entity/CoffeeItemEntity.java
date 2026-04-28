package com.repository.My_System.infraestructure.entity;

import com.repository.My_System.domain.enums.EnumMilck;
import com.repository.My_System.domain.enums.EnumSize;
import com.repository.My_System.domain.enums.EnumTypeCoffee;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private EnumTypeCoffee drink;

    @Enumerated(EnumType.STRING)
    @NotNull
    private EnumSize size;

    @Enumerated(EnumType.STRING)
    @NotNull
    private EnumMilck milk;

    @NotNull
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

}
