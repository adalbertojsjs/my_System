package com.repository.My_System.aplication;

import com.repository.My_System.domain.enums.EnumLocation;
import com.repository.My_System.domain.enums.EnumStatus;
import com.repository.My_System.domain.exceptions.InvalidPaymentExceptions;
import com.repository.My_System.domain.exceptions.OrderNotFoudExceptions;
import com.repository.My_System.domain.model.CoffeeItem;
import com.repository.My_System.domain.model.Order;
import com.repository.My_System.domain.ports.in.Update0rderInPort;
import com.repository.My_System.domain.ports.out.RepisitoryOrders;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@Component
@AllArgsConstructor
public class Update0rderUseCase implements Update0rderInPort {

    private final RepisitoryOrders repisitoryOrders;

    @Override
    public Order updateOrder(UUID orderId, EnumLocation location, List<CoffeeItem> items) {

        validateId(orderId);
        var order = repisitoryOrders.findOrderById(orderId);

        if (order.getStatus() == EnumStatus.CANCELLED || order.getStatus() == EnumStatus.PAID){
            throw  new InvalidPaymentExceptions("Order invalid for update");
        }

        order.setEnumLocation(location);
        order.setItems(items);
        order.setStatus(EnumStatus.READY);

        return repisitoryOrders.save(order);

    }

    private void validateId(UUID id) {
        if (id == null) {
            throw new OrderNotFoudExceptions("The order no found");
        }
    }
}
