package com.repository.My_System.domain.ports.in;

import com.repository.My_System.domain.enums.EnumLocation;
import com.repository.My_System.domain.model.CoffeeItem;
import com.repository.My_System.domain.model.Order;

import java.util.List;

public interface CreatedOrderInPort {

    Order createdOrder(Order order);

}
