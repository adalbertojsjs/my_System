package com.repository.My_System.infraestructure.entity;

import com.repository.My_System.domain.enums.EnumPaymentmethod;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Receipt")
public class ReceiptEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private  UUID codigo;

    private String name;

    private BigDecimal amount;

    private LocalDate date;

    private EnumPaymentmethod paymentmethod;

}
