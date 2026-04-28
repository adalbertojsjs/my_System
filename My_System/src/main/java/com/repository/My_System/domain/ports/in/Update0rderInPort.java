package com.repository.My_System.domain.ports.in;

import com.repository.My_System.domain.enums.EnumLocation;
import com.repository.My_System.domain.model.CoffeeItem;
import com.repository.My_System.domain.model.Order;

import java.util.List;
import java.util.UUID;

public interface Update0rderInPort {

    Order updateOrder(UUID orderId, EnumLocation location, List<CoffeeItem> items);

}
