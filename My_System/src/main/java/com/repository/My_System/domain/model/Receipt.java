package com.repository.My_System.domain.model;

import com.repository.My_System.domain.enums.EnumPaymentmethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Receipt {

    private UUID codigo;

    private String name;

    private BigDecimal amount;

    private LocalDate date;

    private EnumPaymentmethod paymentmethod;


}

