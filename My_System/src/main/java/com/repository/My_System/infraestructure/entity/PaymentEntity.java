package com.repository.My_System.infraestructure.entity;


import com.repository.My_System.domain.enums.EnumPaymentmethod;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "payment")
public class PaymentEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn(name = "order_id")
    private UUID orderId;

    @NotNull
    private String cardHoldernName;

    @NotNull
    private String numCard;

    @NotNull
    private EnumPaymentmethod paymentmethod;

    @NotNull
    private LocalDate date;

    @NotNull
    private BigDecimal costoFinal;


}
