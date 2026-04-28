package com.repository.My_System.domain.ports.in;

import com.repository.My_System.domain.model.Order;

import java.util.UUID;

public interface TakeOrderInPort {

    Order takeOrder(UUID orderId);

}
