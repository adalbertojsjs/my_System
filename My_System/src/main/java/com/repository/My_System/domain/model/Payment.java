package com.repository.My_System.domain.model;

import com.repository.My_System.domain.enums.EnumPaymentmethod;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    private UUID id;

    private UUID orderId;

    private String cardHoldernName;

    private String numCard;

    private EnumPaymentmethod paymentmethod;

    private LocalDate date;

    private BigDecimal costoFinal;

}
