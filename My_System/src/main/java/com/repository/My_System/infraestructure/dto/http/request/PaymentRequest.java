package com.repository.My_System.infraestructure.dto.http.request;

import com.repository.My_System.domain.enums.EnumPaymentmethod;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class PaymentRequest {

    private String cardHoldernName;

    private String numCard;

    private EnumPaymentmethod paymentmethod;

    private UUID orderId;
}
