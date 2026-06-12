package com.repository.My_System.aplication;

import com.repository.My_System.domain.exceptions.OrderNotFoudExceptions;
import com.repository.My_System.domain.model.Order;
import com.repository.My_System.domain.ports.in.SearchOrderByIdInPort;
import com.repository.My_System.domain.ports.out.RepisitoryOrders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Component
public class SearchOrderByIdUseCase implements SearchOrderByIdInPort {

    private final RepisitoryOrders repisitoryOrders;

    @Override
    public Order findById(UUID id) {

        if (isNull(id)){
            throw  new OrderNotFoudExceptions("Id Invalid");
        }

        return repisitoryOrders.findOrderById(id).
                orElseThrow(() -> new OrderNotFoudExceptions("Id not found"));

    }
}
