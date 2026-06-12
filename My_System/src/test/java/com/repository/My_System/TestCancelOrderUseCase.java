package com.repository.My_System;


import com.repository.My_System.aplication.CancelOrderUseCase;
import com.repository.My_System.domain.enums.EnumLocation;
import com.repository.My_System.domain.enums.EnumSize;
import com.repository.My_System.domain.enums.EnumStatus;
import com.repository.My_System.domain.exceptions.OrderNotFoudExceptions;
import com.repository.My_System.domain.model.CoffeeItem;
import com.repository.My_System.domain.model.Order;
import com.repository.My_System.domain.ports.out.RepisitoryOrders;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class TestCancelOrderUseCase {

    @Mock
    RepisitoryOrders repisitoryOrders;

    @InjectMocks
    CancelOrderUseCase useCase;


    @Test
    void shouldCancelOrderSuccessfully(){
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

        var order = Order
                .builder()
                .id(UUID.randomUUID())
                .enumLocation(EnumLocation.TAKE_AWAY)
                .status(EnumStatus.READY)
                .items(items)
                .build();

        when(repisitoryOrders.findOrderById(order.getId())).thenReturn(Optional.of(order));
        when(repisitoryOrders.save(any())).thenReturn(order);

          useCase.cancelOrder(order.getId());

        ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);

         verify(repisitoryOrders).save(captor.capture());
        Order order1 = captor.getValue();

        assertNotNull(order1);
        assertEquals(EnumStatus.CANCELLED, order1.getStatus());

    }

    @Test
    void shouldThrowExceptionWhenOrderIsPaid(){
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

        var order = Order
                .builder()
                .id(UUID.randomUUID())
                .enumLocation(EnumLocation.TAKE_AWAY)
                .status(EnumStatus.PAID)
                .items(items)
                .build();

        when(repisitoryOrders.findOrderById(order.getId())).thenReturn(Optional.of(order));


        RuntimeException exception = assertThrows(RuntimeException.class, ()-> useCase.cancelOrder(order.getId()));

       log.info(exception.getMessage());
        assertEquals("order is paid", exception.getMessage());

        verify(repisitoryOrders,never()).save(any());
    }


    @Test
    void shouldThrowExceptionWhenOrderIsPreparing(){
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

        var order = Order
                .builder()
                .id(UUID.randomUUID())
                .enumLocation(EnumLocation.TAKE_AWAY)
                .status(EnumStatus.PREPARING)
                .items(items)
                .build();

        when(repisitoryOrders.findOrderById(order.getId())).thenReturn(Optional.of(order));

     RuntimeException exception = assertThrows(RuntimeException.class, ()-> useCase.cancelOrder(order.getId()));

     log.info(exception.getMessage());
        assertEquals("order is paid", exception.getMessage());
        verify(repisitoryOrders,never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull(){
        OrderNotFoudExceptions exceptions =  assertThrows(OrderNotFoudExceptions.class,
                () -> useCase.cancelOrder(null));

        log.info(exceptions.getMessage());

        assertEquals("The order no found", exceptions.getMessage());

        verify(repisitoryOrders, never()).save(any());
        verify(repisitoryOrders, never()).findOrderById(any());
    }


    @Test
    void shouldThrowExceptionWhenOrderIsAlreadyCancelled(){

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

        var order = Order
                .builder()
                .id(UUID.randomUUID())
                .enumLocation(EnumLocation.TAKE_AWAY)
                .status(EnumStatus.CANCELLED)
                .items(items)
                .build();

    when(repisitoryOrders.findOrderById(order.getId())).thenReturn(Optional.of(order));

    RuntimeException exception = assertThrows(RuntimeException.class,
            () -> useCase.cancelOrder(order.getId()));

    assertEquals("Order already cancel" , exception.getMessage());

    verify(repisitoryOrders).findOrderById(order.getId());
    verify(repisitoryOrders, never()).save(any());

    }

}
