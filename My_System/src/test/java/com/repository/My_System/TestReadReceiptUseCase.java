package com.repository.My_System;


import com.repository.My_System.aplication.ReadReceiptUseCase;
import com.repository.My_System.domain.enums.EnumPaymentmethod;
import com.repository.My_System.domain.exceptions.InvalidPaymentExceptions;
import com.repository.My_System.domain.model.Payment;
import com.repository.My_System.domain.model.Receipt;
import com.repository.My_System.domain.ports.out.RepositoryPayment;
import com.repository.My_System.domain.ports.out.RepositoryReceip;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestReadReceiptUseCase {


    @Mock
    RepositoryPayment repositoryPayment;

    @Mock
    RepositoryReceip repositoryReceip;

    @InjectMocks
    ReadReceiptUseCase useCase;

    @Test
    void shouldReadReceiptSuccessfully(){
        var payment = Payment.
                builder().
                id(UUID.randomUUID())
                .numCard("38383848448")
                .cardHoldernName("random3333")
                .paymentmethod(EnumPaymentmethod.CreditCard)
                .costoFinal(BigDecimal.valueOf(23.0))
                .build();

        when(repositoryPayment.findPaymentById(payment.getId())).thenReturn(payment);

         useCase.readReceipt(payment.getId());

        ArgumentCaptor<Receipt> captor = ArgumentCaptor.forClass(Receipt.class);

        verify(repositoryReceip).save(captor.capture());

        Receipt receipt = captor.getValue();

        assertNotNull(receipt);
        assertEquals(payment.getCardHoldernName(),receipt.getName());

        verify(repositoryPayment).findPaymentById(payment.getId());
        verify(repositoryReceip).save(any(Receipt.class));

    }


    @Test
    void shouldThrowExceptionWhenPaymentIdIsNull(){

        var payment = Payment.
                builder().
                id(null)
                .numCard("38383848448")
                .cardHoldernName("random3333")
                .paymentmethod(EnumPaymentmethod.CreditCard)
                .costoFinal(BigDecimal.valueOf(23.0))
                .build();

        assertThrows(InvalidPaymentExceptions.class,()-> useCase.readReceipt(payment.getId()));

        verify(repositoryPayment, never()).findPaymentById(payment.getId());
        verify(repositoryReceip, never()).save(any());
    }
}
