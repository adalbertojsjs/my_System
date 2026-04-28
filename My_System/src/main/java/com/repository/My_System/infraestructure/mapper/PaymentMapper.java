package com.repository.My_System.infraestructure.mapper;

import com.repository.My_System.domain.model.Payment;
import com.repository.My_System.infraestructure.entity.PaymentEntity;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {


    public Payment toDomain(PaymentEntity entity) {

        var payment = Payment
                .builder()
                .id(entity.getId())
                .orderId(entity.getOrderId())
                .paymentmethod(entity.getPaymentmethod())
                .numCard(entity.getNumCard())
                .cardHoldernName(entity.getCardHoldernName())
                .date(entity.getDate())
                .costoFinal(entity.getCostoFinal())
                .build();

        return  payment;
    }

    public PaymentEntity toEntity(Payment payment) {
        var entity = PaymentEntity
                .builder()
                .id(payment.getId())
                .orderId(payment.getOrderId())
                .numCard(payment.getNumCard())
                .cardHoldernName(payment.getCardHoldernName())
                .paymentmethod(payment.getPaymentmethod())
                .date(payment.getDate())
                .costoFinal(payment.getCostoFinal())
                .build();
        return entity;
    }
}