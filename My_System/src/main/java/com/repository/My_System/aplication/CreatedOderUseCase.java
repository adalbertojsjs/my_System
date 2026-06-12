package com.repository.My_System.aplication;

import com.repository.My_System.domain.enums.EnumStatus;
import com.repository.My_System.domain.exceptions.OrderNotFoudExceptions;
import com.repository.My_System.domain.model.Order;
import com.repository.My_System.domain.ports.in.CreatedOrderInPort;
import com.repository.My_System.domain.ports.out.RepisitoryOrders;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
@AllArgsConstructor
public class CreatedOderUseCase implements CreatedOrderInPort {

    private final RepisitoryOrders repisitoryOrders;

    @Override
    public Order createdOrder(Order orderR){

        if (isNull(orderR)){
            throw new OrderNotFoudExceptions("order cannot be null");
        }

        var order = Order.builder()
                .enumLocation(orderR.getEnumLocation())
                .status(EnumStatus.READY)
                .items(orderR.getItems())
                .build();

        return  repisitoryOrders.save(order);

    }
}
