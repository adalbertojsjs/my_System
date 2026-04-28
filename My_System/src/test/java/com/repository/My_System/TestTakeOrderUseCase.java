package com.repository.My_System;


import com.repository.My_System.aplication.TakeOrderUseCase;
import com.repository.My_System.domain.enums.EnumStatus;
import com.repository.My_System.domain.model.Order;
import com.repository.My_System.domain.ports.out.RepisitoryOrders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestTakeOrderUseCase {

    @Mock
    RepisitoryOrders repisitoryOrders;

    @InjectMocks
    TakeOrderUseCase useCase;

    @Test
    void shouldTakeOrderSuccessfully(){

        var order = Order.
                builder().
                id(UUID.randomUUID()).
                status(EnumStatus.PAID).
                build();


        when(repisitoryOrders.findOrderById(order.getId())).thenReturn(order);

        var reslt = useCase.takeOrder(order.getId());

        System.out.println(reslt.getStatus());

        assertNotNull(reslt);
        assertEquals(EnumStatus.TAKEN, reslt.getStatus());

        verify(repisitoryOrders).findOrderById(order.getId());
    }


    @Test
    void shouldThrowExceptionWhenOrderIsPaid(){

        var order = Order.
                builder().
                id(UUID.randomUUID()).
                status(EnumStatus.READY).
                build();

        when(repisitoryOrders.findOrderById(order.getId())).thenReturn(order);

        assertThrows(IllegalStateException.class, ()-> useCase.takeOrder(order.getId()));

        verify(repisitoryOrders, never()).save(any());
        verify(repisitoryOrders).findOrderById(order.getId());
    }
}
