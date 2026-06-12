package com.repository.My_System;

import com.repository.My_System.aplication.SearchOrderByIdUseCase;
import com.repository.My_System.domain.enums.EnumLocation;
import com.repository.My_System.domain.enums.EnumStatus;
import com.repository.My_System.domain.exceptions.OrderNotFoudExceptions;
import com.repository.My_System.domain.model.CoffeeItem;
import com.repository.My_System.domain.model.Order;
import com.repository.My_System.domain.ports.out.RepisitoryOrders;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.longThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class TestSearchOrderByIdUseCase {

    @Mock
    RepisitoryOrders repisitoryOrders;

    @InjectMocks
    SearchOrderByIdUseCase useCase;


    @Test
    void shouldFindOrderByIdSuccessfully(){

        List<CoffeeItem> items = List.of(new CoffeeItem(), new CoffeeItem());

        var order = Order
                .builder()
                .id(UUID.randomUUID())
                .enumLocation(EnumLocation.TAKE_AWAY)
                .items(items)
                .status(EnumStatus.READY)
                .build();

        when(repisitoryOrders.findOrderById(order.getId())).thenReturn(Optional.of(order));

        var result = useCase.findById(order.getId());

        assertEquals(EnumLocation.TAKE_AWAY,result.getEnumLocation());
        assertEquals(2,result.getItems().size());
        assertEquals(EnumStatus.READY, result.getStatus());

        verify(repisitoryOrders).findOrderById(order.getId());

    }

    @Test
    void shouldThrowExceptionWhenIdIsNull(){

        OrderNotFoudExceptions exceptions = assertThrows(OrderNotFoudExceptions.class,
                () -> useCase.findById(null));

        log.info(exceptions.getMessage());

        assertEquals("Id Invalid", exceptions.getMessage());

        verify(repisitoryOrders, never()).findOrderById(any());
    }
}
