package com.repository.My_System;


import com.repository.My_System.aplication.PayOrderUseCase;
import com.repository.My_System.domain.enums.*;
import com.repository.My_System.domain.exceptions.InvalidPaymentExceptions;
import com.repository.My_System.domain.exceptions.OrderNotFoudExceptions;
import com.repository.My_System.domain.model.CoffeeItem;
import com.repository.My_System.domain.model.Order;
import com.repository.My_System.domain.model.Payment;
import com.repository.My_System.domain.ports.out.RepisitoryOrders;
import com.repository.My_System.domain.ports.out.RepositoryPayment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestPayOrderUseCase {

    @Mock
    RepositoryPayment repositoryPayment;

    @Mock
    RepisitoryOrders repisitoryOrders;

    @InjectMocks
    PayOrderUseCase useCase;

//    @InjectMocks
//    private Validate validate;


    @Test
      void shouldPayOrderSuccessfully(){
        List<CoffeeItem> items = List.of(
                CoffeeItem.builder()
                        .size(EnumSize.SMALL)
                        .quantity(2)
                        .build(),

                CoffeeItem.builder()
                        .size(EnumSize.LARGE)
                        .quantity(3)
                        .build()
        );

        Order order = Order.
                builder().
                items(items).
                id(UUID.randomUUID()).
                status(EnumStatus.READY).
                build();

        Payment payment = Payment.
                builder().
                numCard("339393939").
                paymentmethod(EnumPaymentmethod.CreditCard).
                cardHoldernName("random47474").
                orderId(order.getId()).
                build();


        when(repisitoryOrders.findOrderById(order.getId())).thenReturn(order);
        when(repisitoryOrders.save(any())).thenReturn(order);
        when(repositoryPayment.save(any())).thenReturn(payment);

        useCase.payOrder(order.getId(), payment);

        ArgumentCaptor<Payment> captor =
                ArgumentCaptor.forClass(Payment.class);

        verify(repositoryPayment).save(captor.capture());

        Payment savedPayment = captor.getValue();

        assertEquals(BigDecimal.valueOf(23.0), savedPayment.getCostoFinal());
        assertEquals(EnumStatus.PAID , order.getStatus());

        verify(repisitoryOrders).findOrderById(order.getId());
        verify(repisitoryOrders).save(any(Order.class));
    }


    @Test
   void shouldThrowExceptionWhenOrderIdIsNull(){
        Order order = Order.
                builder().
                items(List.of(new CoffeeItem(),new CoffeeItem())).
                id(null).
                status(EnumStatus.READY).
                build();

        Payment payment = Payment.
                builder().
                numCard("339393939").
                paymentmethod(EnumPaymentmethod.CreditCard).
                cardHoldernName("random47474").
                orderId(order.getId()).
                build();

        assertThrows(OrderNotFoudExceptions.class, ()-> useCase.payOrder(order.getId(),payment));

        verify(repisitoryOrders,never()).findOrderById(any());
        verify(repositoryPayment, never()).save(any(Payment.class));
    }

    @Test
    void shouldThrowExceptionWhenCardNumberIsInvalid(){
        Order order = Order.
                builder().
                items(List.of(new CoffeeItem(),new CoffeeItem())).
                id(UUID.randomUUID()).
                status(EnumStatus.READY).
                build();

        Payment payment = Payment.
                builder().
                numCard("3").
                paymentmethod(EnumPaymentmethod.CreditCard).
                cardHoldernName("random47474").
                orderId(order.getId()).
                build();

        when(repisitoryOrders.findOrderById(order.getId())).thenReturn(order);

        assertThrows(InvalidPaymentExceptions.class,()-> useCase.payOrder(order.getId(),payment));

        verify(repisitoryOrders).findOrderById(any());
        verify(repositoryPayment, never()).save(any(Payment.class));

    }

    @Test
   void shouldThrowExceptionWhenOrderStatusIsNotReady(){

        Order order = Order.
                builder().
                items(List.of(new CoffeeItem(),new CoffeeItem())).
                id(UUID.randomUUID()).
                status(EnumStatus.CANCELLED).
                build();

        Payment payment = Payment.
                builder().
                numCard("339393939").
                paymentmethod(EnumPaymentmethod.CreditCard).
                cardHoldernName("random47474").
                orderId(order.getId()).
                build();

        when(repisitoryOrders.findOrderById(order.getId())).thenReturn(order);

        assertThrows(InvalidPaymentExceptions.class, ()-> useCase.payOrder(order.getId(),payment));

        verify(repisitoryOrders).findOrderById(order.getId());
        verify(repositoryPayment, never()).save(any(Payment.class));
    }



    @Test
    void shouldThrowExceptionWhenOrderIsAlreadyPaid(){
        Order order = Order.
                builder().
                items(List.of(new CoffeeItem(),new CoffeeItem())).
                id(UUID.randomUUID()).
                status(EnumStatus.PAID).
                build();

        Payment payment = Payment.
                builder().
                numCard("339393939").
                paymentmethod(EnumPaymentmethod.CreditCard).
                cardHoldernName("random47474").
                orderId(order.getId()).
                build();

        when(repisitoryOrders.findOrderById(order.getId())).thenReturn(order);

        assertThrows(InvalidPaymentExceptions.class,()-> useCase.payOrder(order.getId(),payment));

        verify(repisitoryOrders).findOrderById(order.getId());
        verify(repositoryPayment, never()).save(any(Payment.class));

    }


    @Test
    void shouldThrowExceptionWhenCardNumberIsNull() {
        Order order = Order.
                builder().
                items(List.of(new CoffeeItem(), new CoffeeItem())).
                id(UUID.randomUUID()).
                status(EnumStatus.READY).
                build();

        Payment payment = Payment.
                builder().
                numCard(null).
                paymentmethod(EnumPaymentmethod.CreditCard).
                cardHoldernName("random47474").
                orderId(order.getId()).
                build();

        when(repisitoryOrders.findOrderById(order.getId())).thenReturn(order);

        assertThrows(InvalidPaymentExceptions.class, () -> useCase.payOrder(order.getId(), payment));

         verify(repisitoryOrders).findOrderById(order.getId());
         verify(repositoryPayment, never()).save(any(Payment.class));
    }

    @Test
    void shouldThrowExceptionWhenPaymentMethodIsNull(){

        Order order = Order.
                builder().
                items(List.of(new CoffeeItem(), new CoffeeItem())).
                id(UUID.randomUUID()).
                status(EnumStatus.READY).
                build();

        Payment payment = Payment.
                builder().
                numCard("333333999").
                paymentmethod(null).
                cardHoldernName("random47474").
                orderId(order.getId()).
                build();

        when(repisitoryOrders.findOrderById(order.getId())).thenReturn(order);

        assertThrows(InvalidPaymentExceptions.class, () -> useCase.payOrder(order.getId(), payment));

        verify(repisitoryOrders).findOrderById(order.getId());
        verify(repositoryPayment, never()).save(any(Payment.class));
    }

    @Test
    void shouldThrowExceptionWhenCardHolderNameIsNull(){

        Order order = Order.
                builder().
                items(List.of(new CoffeeItem(), new CoffeeItem())).
                id(UUID.randomUUID()).
                status(EnumStatus.READY).
                build();

        Payment payment = Payment.
                builder().
                numCard("333333999").
                paymentmethod(EnumPaymentmethod.CreditCard).
                cardHoldernName(null).
                orderId(order.getId()).
                build();

        when(repisitoryOrders.findOrderById(order.getId())).thenReturn(order);

        assertThrows(InvalidPaymentExceptions.class, () -> useCase.payOrder(order.getId(), payment));

        verify(repisitoryOrders).findOrderById(order.getId());
        verify(repositoryPayment, never()).save(any(Payment.class));
    }

}
