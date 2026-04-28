package com.repository.My_System.aplication;

import com.repository.My_System.domain.enums.EnumStatus;
import com.repository.My_System.domain.model.Order;
import com.repository.My_System.domain.ports.in.TakeOrderInPort;
import com.repository.My_System.domain.ports.out.RepisitoryOrders;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class TakeOrderUseCase implements TakeOrderInPort {

    private final RepisitoryOrders repisitoryOrders;

    @Override
    public Order takeOrder(UUID orderId) {

        var order = repisitoryOrders.findOrderById(orderId);

        if (order.getStatus() != EnumStatus.PAID) {
            throw new IllegalStateException("Order is not Paid");
        }

        order.setStatus(EnumStatus.TAKEN);

        return order;
    }
}
