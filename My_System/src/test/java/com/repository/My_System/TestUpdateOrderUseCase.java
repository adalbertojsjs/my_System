package com.repository.My_System;


import com.repository.My_System.aplication.Update0rderUseCase;
import com.repository.My_System.domain.enums.EnumLocation;
import com.repository.My_System.domain.enums.EnumSize;
import com.repository.My_System.domain.enums.EnumStatus;
import com.repository.My_System.domain.exceptions.InvalidPaymentExceptions;
import com.repository.My_System.domain.exceptions.OrderNotFoudExceptions;
import com.repository.My_System.domain.model.CoffeeItem;
import com.repository.My_System.domain.model.Order;
import com.repository.My_System.domain.ports.out.RepisitoryOrders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestUpdateOrderUseCase {

    @Mock
    RepisitoryOrders repisitoryOrders;

    @InjectMocks
    Update0rderUseCase useCase;


    @Test
    void shouldUpdateOrderSuccessfully(){

        var orderId = UUID.randomUUID();

        var order = Order.
                builder().
                id(orderId).
                enumLocation(EnumLocation.IN_STORE).
                items(List.of(new CoffeeItem(),new CoffeeItem())).
                status(EnumStatus.PREPARING).
                build();

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


        when(repisitoryOrders.save(order)).thenReturn(order);
        when(repisitoryOrders.findOrderById(orderId)).thenReturn(order);

        var result = useCase.updateOrder(orderId,EnumLocation.TAKE_AWAY,items);

        System.out.println(result.getStatus());
        System.out.println(result.getEnumLocation());

        assertNotNull(result);
        assertEquals(EnumStatus.READY,result.getStatus());
        assertEquals(items, result.getItems());
        assertEquals(EnumLocation.TAKE_AWAY,result.getEnumLocation());

        verify(repisitoryOrders).save(any(Order.class));
        verify(repisitoryOrders).findOrderById(orderId);
    }

    @Test
    void shouldThrowExceptionWhenOrderIdIsNull(){

        var order = Order.
                builder().
                id(null).
                enumLocation(EnumLocation.IN_STORE).
                items(List.of(new CoffeeItem(),new CoffeeItem())).
                status(EnumStatus.PREPARING).
                build();

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

        assertThrows(OrderNotFoudExceptions.class,()->
                useCase.updateOrder(order.getId(),EnumLocation.TAKE_AWAY,items));

        verify(repisitoryOrders,never()).save(any());
        verify(repisitoryOrders, never()).findOrderById(order.getId());
    }

    @Test
    void shouldThrowExceptionWhenOrderStatusIsCancel(){

        var order = Order.
                builder().
                id(UUID.randomUUID()).
                enumLocation(EnumLocation.IN_STORE).
                items(List.of(new CoffeeItem(),new CoffeeItem())).
                status(EnumStatus.CANCELLED).
                build();

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

        when(repisitoryOrders.findOrderById(order.getId())).thenReturn(order);

        assertThrows(InvalidPaymentExceptions.class,
                ()-> useCase.updateOrder(order.getId(),EnumLocation.TAKE_AWAY,items));

        verify(repisitoryOrders,never()).save(any());
        verify(repisitoryOrders).findOrderById(order.getId());
    }

    @Test
    void shouldThrowExceptionWhenOrderStatusIsPaid(){

        var order = Order.
                builder().
                id(UUID.randomUUID()).
                enumLocation(EnumLocation.IN_STORE).
                items(List.of(new CoffeeItem(),new CoffeeItem())).
                status(EnumStatus.PAID).
                build();

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

        when(repisitoryOrders.findOrderById(order.getId())).thenReturn(order);

        assertThrows(InvalidPaymentExceptions.class,
                ()-> useCase.updateOrder(order.getId(),EnumLocation.TAKE_AWAY,items));

        verify(repisitoryOrders,never()).save(any());
        verify(repisitoryOrders).findOrderById(order.getId());
    }
}
