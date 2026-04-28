package com.repository.My_System;


import com.repository.My_System.aplication.CreatedOderUseCase;
import com.repository.My_System.domain.enums.EnumLocation;
import com.repository.My_System.domain.enums.EnumSize;
import com.repository.My_System.domain.enums.EnumStatus;
import com.repository.My_System.domain.exceptions.OrderNotFoudExceptions;
import com.repository.My_System.domain.model.CoffeeItem;
import com.repository.My_System.domain.model.Order;
import com.repository.My_System.domain.ports.out.RepisitoryOrders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestCreatedOrderUseCase {

    @Mock
    RepisitoryOrders repisitoryOrders;

    @InjectMocks
    CreatedOderUseCase useCase;

    @Test
    void shouldCreatedOrderSuccessfully(){

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

        var order = Order.
                builder().
                enumLocation(EnumLocation.IN_STORE).
                items(items).
                build();

        when(repisitoryOrders.save(any(Order.class))).thenReturn(order);

         useCase.createdOrder(order);

        ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);

        verify(repisitoryOrders).save(captor.capture());

        Order orderSaved = captor.getValue();

        assertNotNull(orderSaved);
        assertEquals(EnumStatus.READY, orderSaved.getStatus());

        verify(repisitoryOrders).save(any(Order.class));
    }

    @Test
    void shouldThrowExceptionWhenOrderIsNull() {

        assertThrows(OrderNotFoudExceptions.class, () -> useCase.createdOrder(null));

        verify(repisitoryOrders, never()).save(any());

    }
}
