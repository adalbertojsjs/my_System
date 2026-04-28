package com.repository.My_System.aplication;

import com.repository.My_System.domain.enums.EnumStatus;
import com.repository.My_System.domain.exceptions.OrderNotFoudExceptions;
import com.repository.My_System.domain.ports.in.CancelOrderInPort;
import com.repository.My_System.domain.ports.out.RepisitoryOrders;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class CancelOrderUseCase implements CancelOrderInPort {

    private final RepisitoryOrders repisitoryOrders;

    @Override
    public void cancelOrder(UUID orderId) {
        validateId(orderId);

        var order = repisitoryOrders.findOrderById(orderId);

        if (order.getStatus() == EnumStatus.PAID || order.getStatus() == EnumStatus.PREPARING){
            throw new RuntimeException("order is paid ");
        }

        if (order.getStatus() == EnumStatus.CANCELLED){
            throw  new RuntimeException("Order already cancel");
        }
        order.setStatus(EnumStatus.CANCELLED);
        repisitoryOrders.save(order);
    }


    private void validateId(UUID id) {
        if (id == null) {
            throw new OrderNotFoudExceptions("The order no found");
        }
    }
}
