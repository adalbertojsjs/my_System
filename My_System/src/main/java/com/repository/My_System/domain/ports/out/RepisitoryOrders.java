package com.repository.My_System.domain.ports.out;

import com.repository.My_System.domain.model.Order;

import java.util.UUID;

public interface RepisitoryOrders {

    Order findOrderById(UUID orderId);
    Order save(Order order);
    void deleteById(UUID orderId);//todo hacer el caso de uso
}
