package com.repository.My_System.aplication;

import com.repository.My_System.domain.enums.EnumStatus;
import com.repository.My_System.domain.exceptions.OrderNotFoudExceptions;
import com.repository.My_System.domain.model.Order;
import com.repository.My_System.domain.ports.in.CreatedOrderInPort;
import com.repository.My_System.domain.ports.out.RepisitoryOrders;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreatedOderUseCase implements CreatedOrderInPort {

    private final RepisitoryOrders repisitoryOrders;

    @Override
    public Order createdOrder(Order orderR){

        if (orderR == null){
            throw new OrderNotFoudExceptions("order cannot be null");
        }

        var order = Order.builder()
                .enumLocation(orderR.getEnumLocation())
//                .quantity(orderR.getQuantity())
                .status(EnumStatus.READY)
                .items(orderR.getItems())
                .build();

        return  repisitoryOrders.save(order);

    }
}
